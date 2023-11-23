package cams.option.enquiry;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.EnquiryManager;
import cams.utils.Dismiss;
import cams.utils.Page;

public class CampEnquiriesOptions extends DismissableSelectableOptions {

    private boolean viewResolved;
    private String noCampTitle;
    private ArrayList<Integer> enquiryIDs;
    private int selectedCampID;

    public CampEnquiriesOptions(boolean viewResolved, int selectedCampID) {
        this.viewResolved = viewResolved;
        this.fetchCampEnquiries();
        this.selectedCampID = selectedCampID;
    }

    @Override
    public void display(String title) {
        fetchCampEnquiries();
        if (super.getOptionsSize() == 0) {
            Page.header(this.noCampTitle);
        } else {
            super.display(title);
        }
    }

    @Override
    public int selection() {
        int option = super.selection();
        if (option == Dismiss.intOption()) { return option; }
        return this.enquiryIDs.get(option - 1);
    }

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
