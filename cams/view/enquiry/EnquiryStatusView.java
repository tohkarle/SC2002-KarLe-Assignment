package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;

public class EnquiryStatusView extends View {

    private UserManager userManager;
    private CampManager campManager;
    private EnquiryManager enquiryManager;

    // Options for this view:
    private Options enquiryStatusOptions;

    public EnquiryStatusView(Navigation navigation, UserManager userManager, CampManager campManager, EnquiryManager enquiryManager) {
        super(navigation);
        this.userManager = userManager;
        this.campManager = campManager;
        this.enquiryManager  = enquiryManager;
    }

    public void render() {
        // Display suggestion type options
        enquiryStatusOptions = super.getOptions("enquiry.EnquiryStatusOptions");
        enquiryStatusOptions.display("Select enquiry status:");

        // Let user select which enquiry type to view
        int option = enquiryStatusOptions.selection();
        if (option == Dismiss.intOption()) {
            super.getNavigation().dismissView();
            return; 
        }

        // Navigate to respective view
        enquiryManager.setViewResolvedEnquiries(option != 1);

        if (userManager.isStaff() || campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), campManager.getSelectedCampID())) {
            System.out.println("ENQUIRY: " + enquiryManager.getViewResolvedEnquiries());
            super.getNavigation().navigateTo("enquiry.CampEnquiriesView");
        } else {
            super.getNavigation().navigateTo("enquiry.AttendeeCampEnquiriesView");
        }
    }
}
