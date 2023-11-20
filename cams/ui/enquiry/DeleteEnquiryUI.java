package cams.ui.enquiry;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.ui.ConfirmOrDiscardUI;

public class DeleteEnquiryUI implements UI {

    private Navigation navigation;
    private int selectedEnquiryID;

    public DeleteEnquiryUI(Navigation navigation, int selectedEnquiryID) {
        this.navigation = navigation;
        this.selectedEnquiryID = selectedEnquiryID;
    }

    @Override
    public void body() {

        EnquiryManager enquiryManager = EnquiryManager.getInstance();
        IntInput confirm = new ConfirmOrDiscardUI();

        // Confirm delete or discard and go back
        if (confirm.getValidInt("Confirm delete?") != 1) { return; }

        // Delete enquiry
        enquiryManager.deleteEnquiry(selectedEnquiryID);
        LoadingIndicator.deleteLoadingIndicator("enquiry");
        navigation.dismissView();
    }
}
