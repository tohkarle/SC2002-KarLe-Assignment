package cams.option.enquiry;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.Page;

public class AttendeeCampEnquiriesOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> enquiryIDs;
    private UserManager userManager;
    private CampManager campManager;
    private EnquiryManager enquiryManager;

    public AttendeeCampEnquiriesOptions(UserManager userManager, CampManager campManager, EnquiryManager enquiryManager) {
        this.userManager = userManager;
        this.campManager = campManager;
        this.enquiryManager = enquiryManager;
        this.fetchStudentCampEnquiries();
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

        // Fetch enquiries from camp, resolved or not
        if (enquiryManager.getViewResolvedEnquiries()) {

            this.noCampTitle = "No resolved enquiry for this camp at the moment.";
            super.setOptions(enquiryManager.getResolvedStudentCampEnquiryTitles(userManager.getCurrentUser().getName(), campManager.getSelectedCampID()));
            this.enquiryIDs = enquiryManager.getResolvedStudentCampEnquiryIDs(userManager.getCurrentUser().getName(), campManager.getSelectedCampID());

        } else {

            this.noCampTitle = "No pending enquiry for this camp at the moment.";
            super.setOptions(enquiryManager.getNotResolvedStudentCampEnquiryTitles(userManager.getCurrentUser().getName(), campManager.getSelectedCampID()));
            this.enquiryIDs = enquiryManager.getNotResolvedStudentCampEnquiryIDs(userManager.getCurrentUser().getName(), campManager.getSelectedCampID());

        }
    }
}
