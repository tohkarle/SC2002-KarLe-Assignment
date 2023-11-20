package cams.ui.enquiry;

import cams.components.input.ConfirmOrDiscard;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.utils.LoadingIndicator;

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
        IntInput confirm = new ConfirmOrDiscard();

        // Confirm delete or discard and go back
        if (confirm.getValidInt("Confirm delete?") != 1) { return; }

        // Delete enquiry
        enquiryManager.deleteEnquiry(selectedEnquiryID);
        LoadingIndicator.deleteLoadingIndicator("enquiry");
        navigation.dismissView();
    }
}
