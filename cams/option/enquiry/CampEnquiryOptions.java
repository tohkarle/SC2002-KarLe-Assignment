package cams.option.enquiry;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.utils.Dismiss;
import cams.utils.Page;

public class CampEnquiryOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> enquiryIDs;
    private CampManager campManager;
    private EnquiryManager enquiryManager;

    public CampEnquiryOptions(CampManager campManager, EnquiryManager enquiryManager) {
        this.campManager = campManager;
        this.enquiryManager = enquiryManager;
        this.fetchCampEnquiries();
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

        // Fetch enquiries from camp, resolved or not
        if (enquiryManager.getViewResolvedEnquiries()) {

            this.noCampTitle = "No resolved enquiry for this camp at the moment. Please come back at a later time.";
            super.setOptions(enquiryManager.getResolvedCampEnquiryTitles(campManager.getSelectedCampID()));
            this.enquiryIDs = enquiryManager.getResolvedCampEnquiryIDs(campManager.getSelectedCampID());

        } else {

            this.noCampTitle = "No pending enquiry for this camp at the moment. Please come back at a later time.";
            super.setOptions(enquiryManager.getNotResolvedCampEnquiryTitles(campManager.getSelectedCampID()));
            this.enquiryIDs = enquiryManager.getNotResolvedCampEnquiryIDs(campManager.getSelectedCampID());

        }
    }
}
