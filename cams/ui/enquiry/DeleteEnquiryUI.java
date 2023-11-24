package cams.ui.enquiry;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.utils.LoadingIndicator;

/**
 * UI object for deleting an enquiry
 */
public class DeleteEnquiryUI implements UI {

    private Navigation navigation;
    private Input getInput;
    private int selectedEnquiryID;

    /**
     * Initialize the DeleteEnquiryUI
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get user input
     * @param selectedEnquiryID ID of the enquiry to be deleted
     */
    public DeleteEnquiryUI(Navigation navigation, Input getInput, int selectedEnquiryID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedEnquiryID = selectedEnquiryID;
    }

    /**
     * Executes user interaction logic for deleting an enquiry
     */
    @Override
    public void body() {

        EnquiryManager enquiryManager = EnquiryManager.getInstance();

        // Confirm delete or discard and go back
        if (getInput.confirmOrDiscard("Confirm delete?") != 1) { return; }

        // Delete enquiry
        enquiryManager.deleteEnquiry(selectedEnquiryID);
        LoadingIndicator.deleteLoadingIndicator("enquiry");
        navigation.dismissView();
    }
}
