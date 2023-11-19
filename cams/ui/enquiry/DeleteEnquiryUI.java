package cams.ui.enquiry;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;

public class DeleteEnquiryUI implements UI {

    private Navigation navigation;
    private EnquiryManager enquiryManager;
    private IntInput confirm;

    public DeleteEnquiryUI(Navigation navigation, EnquiryManager enquiryManager, IntInput confirm) {
        this.navigation = navigation;
        this.enquiryManager = enquiryManager;
        this.confirm = confirm;
    }

    @Override
    public void body() {
        // Confirm delete or discard and go back
        if (confirm.getValidInt("Confirm delete?") != 1) { return; }

        // Delete enquiry
        enquiryManager.deleteEnquiry(enquiryManager.getSelectedEnquiryID());
        LoadingIndicator.deleteLoadingIndicator("enquiry");
        navigation.dismissView();
    }
}
