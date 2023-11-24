package cams.option.enquiry;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.EnquiryManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.Page;

/**
 * Options object for selecting an enquiry in the AttendeeCampEnquiriesView
 */
public class AttendeeCampEnquiriesOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> enquiryIDs;
    private boolean viewResolved;
    private int selectedCampID;

    /**
     * Initialize the AttendeeCampEnquiries options
     * @param viewResolved Whether to view resolved enquiries or not
     * @param selectedCampID The camp ID of the camp to view enquiries for
     */
    public AttendeeCampEnquiriesOptions(boolean viewResolved, int selectedCampID) {
        this.viewResolved = viewResolved;
        this.fetchStudentCampEnquiries();
        this.selectedCampID = selectedCampID;
    }

    @Override
    public void display(String title) {
        fetchStudentCampEnquiries();
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

    private void fetchStudentCampEnquiries() {

        UserManager userManager = UserManager.getInstance();
        EnquiryManager enquiryManager = EnquiryManager.getInstance();

        // Fetch enquiries from camp, resolved or not
        if (viewResolved) {

            this.noCampTitle = "No resolved enquiry for this camp at the moment.";
            super.setOptions(enquiryManager.getResolvedStudentCampEnquiryTitles(userManager.getCurrentUser().getName(), selectedCampID));
            this.enquiryIDs = enquiryManager.getResolvedStudentCampEnquiryIDs(userManager.getCurrentUser().getName(), selectedCampID);

        } else {

            this.noCampTitle = "No pending enquiry for this camp at the moment.";
            super.setOptions(enquiryManager.getNotResolvedStudentCampEnquiryTitles(userManager.getCurrentUser().getName(), selectedCampID));
            this.enquiryIDs = enquiryManager.getNotResolvedStudentCampEnquiryIDs(userManager.getCurrentUser().getName(), selectedCampID);

        }
    }
}
