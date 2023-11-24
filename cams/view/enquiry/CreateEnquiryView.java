package cams.view.enquiry;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.enquiry.CreateEnquiryUI;
import cams.utils.Page;

/**
 * View object for Create Enquiry page
 */
public class CreateEnquiryView implements View {

    private Navigation navigation;
    private int selectedCampID;

    /**
     * Initialize the CreateEnquiryView
     * @param navigation Navigation object used to control navigation of the application
     * @param selectedCampID int of the selected camp ID
     */
    public CreateEnquiryView(Navigation navigation, int selectedCampID) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

    /**
     * Render the CreateEnquiryView
     */
    public void render() {
        Page.header("Please enter a title and content for the enquiry.");
        UI createEnquiryUi = new CreateEnquiryUI(navigation, selectedCampID);
        createEnquiryUi.body();
    }
}
