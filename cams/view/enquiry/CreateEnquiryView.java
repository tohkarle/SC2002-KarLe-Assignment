package cams.view.enquiry;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.enquiry.CreateEnquiryUI;
import cams.utils.Page;

public class CreateEnquiryView implements View {

    private Navigation navigation;
    private int selectedCampID;

    public CreateEnquiryView(Navigation navigation, int selectedCampID) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

    public void render() {
        Page.header("Please enter a title and content for the enquiry.");
        UI createEnquiryUi = new CreateEnquiryUI(navigation, selectedCampID);
        createEnquiryUi.body();
    }
}
