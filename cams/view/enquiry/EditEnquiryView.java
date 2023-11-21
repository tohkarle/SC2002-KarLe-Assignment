package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.model.Enquiry;
import cams.option.enquiry.EditEnquiryOptions;
import cams.ui.enquiry.EditEnquiryUI;
import cams.utils.Dismiss;

public class EditEnquiryView implements View {

    private Navigation navigation;
    private Input getInput;
    private Enquiry enquiry;

    public EditEnquiryView(Navigation navigation, Input getInput, Enquiry enquiry) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.enquiry = enquiry;
    }

    public void render() {

        Options editEnquiryOptions = new EditEnquiryOptions(enquiry);

        // Display enquiry info for editing
        editEnquiryOptions.display("Select the field you want to edit: ");

        // Let user choose the field to edit
        int selectedEnquiryInfo = editEnquiryOptions.selection();
        if (selectedEnquiryInfo == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        UI editEnquiryUI = new EditEnquiryUI(navigation, getInput, selectedEnquiryInfo, enquiry);
        editEnquiryUI.body();
    }
}
