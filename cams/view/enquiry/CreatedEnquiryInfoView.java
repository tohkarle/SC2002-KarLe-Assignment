package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.EnquiryManager;
import cams.model.Enquiry;
import cams.option.enquiry.CreatedEnquiryInfoOptions;
import cams.ui.enquiry.DeleteEnquiryUI;
import cams.utils.Dismiss;

public class CreatedEnquiryInfoView implements View {

    private Navigation navigation;
    private Input getInput;
    private int selectedEnquiryID;

    public CreatedEnquiryInfoView(Navigation navigation, Input getInput, int selectedEnquiryID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedEnquiryID = selectedEnquiryID;
    }

    public void render() {

        EnquiryManager enquiryManager = EnquiryManager.getInstance();
        Enquiry enquiry = enquiryManager.getEnquiry(selectedEnquiryID);
        Options createdSuggestionOptions = new CreatedEnquiryInfoOptions(enquiry);

        // Display created enquiry details
        createdSuggestionOptions.display("Enquiry details: ");

        // Allow student to go back or edit enquiry and delete enquiry
        int option = createdSuggestionOptions.selection();
        if (option == Dismiss.intOption() ) { 
            navigation.dismissView();
            return; 
        }

        switch (option) {
            case 1:
                navigation.navigateTo(new EditEnquiryView(navigation, getInput, enquiry));
                break;
            case 2:
                UI deleteEnquiryUI = new DeleteEnquiryUI(navigation, getInput, selectedEnquiryID);
                deleteEnquiryUI.body();
                break;
        }
    }
}
