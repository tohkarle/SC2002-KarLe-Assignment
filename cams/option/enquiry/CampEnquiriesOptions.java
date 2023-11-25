package cams.option.enquiry;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.EnquiryManager;
import cams.utils.Dismiss;
import cams.utils.Page;

/**
 * Options object for selecting an enquiry in the CampEnquiriesView
 */
public class CampEnquiriesOptions extends DismissableSelectableOptions {

    private boolean viewResolved;
    private String noCampTitle;
    private ArrayList<Integer> enquiryIDs;
    private int selectedCampID;

    /**
     * Initialize the CampEnquiries options
     * @param viewResolved Whether to view resolved enquiries or not
     * @param selectedCampID The camp ID of the camp to view enquiries for
     */
    public CampEnquiriesOptions(boolean viewResolved, int selectedCampID) {
        this.viewResolved = viewResolved;
        this.fetchCampEnquiries();
        this.selectedCampID = selectedCampID;
    }


    /**
     * The method to display the options
     * @param title The title of what the options are about
     */
    @Override
    public void display(String title) {
        fetchCampEnquiries();
        if (super.getOptionsSize() == 0) {
            Page.header(this.noCampTitle);
        } else {
            super.display(title);
        }
    }


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    @Override
    public int selection() {
        int option = super.selection();
        if (option == Dismiss.intOption()) { return option; }
        return this.enquiryIDs.get(option - 1);
    }


    /**
     * A method to build the options list
     */
    private void fetchCampEnquiries() {
        
        EnquiryManager enquiryManager = EnquiryManager.getInstance();

        // Fetch enquiries from camp, resolved or not
        if (viewResolved) {

            this.noCampTitle = "No resolved enquiry for this camp at the moment. Please come back at a later time.";
            super.setOptions(enquiryManager.getResolvedCampEnquiryTitles(selectedCampID));
            this.enquiryIDs = enquiryManager.getResolvedCampEnquiryIDs(selectedCampID);

        } else {

            this.noCampTitle = "No pending enquiry for this camp at the moment. Please come back at a later time.";
            super.setOptions(enquiryManager.getNotResolvedCampEnquiryTitles(selectedCampID));
            this.enquiryIDs = enquiryManager.getNotResolvedCampEnquiryIDs(selectedCampID);

        }
    }
}
