package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.option.enquiry.EnquiryStatusOptions;
import cams.utils.Dismiss;

public class EnquiryStatusView implements View {

    private Navigation navigation;
    private Input getInput;
    private int selectedCampID;

    public EnquiryStatusView(Navigation navigation, Input getInput, int selectedCampID) {
        this.navigation = navigation;
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

    public void render() {

        UserManager userManager = UserManager.getInstance();
        CampManager campManager = CampManager.getInstance();
        Options enquiryStatusOptions = new EnquiryStatusOptions();

        // Display suggestion type options
        enquiryStatusOptions.display("Select enquiry status:");

        // Let user select which enquiry type to view
        int option = enquiryStatusOptions.selection();
        if (option == Dismiss.intOption()) {
            navigation.dismissView();
            return; 
        }

        boolean viewResolved = (option != 1);

        if (userManager.isStaff() || campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), selectedCampID)) {
            navigation.navigateTo(new CampEnquiriesView(navigation, getInput, viewResolved, selectedCampID));
        } else {
            navigation.navigateTo(new AttendeeCampEnquiriesView(navigation, getInput, viewResolved, selectedCampID));
        }
    }
}
