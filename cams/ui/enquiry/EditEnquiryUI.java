package cams.ui.enquiry;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.model.Enquiry;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;

public class EditEnquiryUI implements UI {

    private Navigation navigation;
    private Input getInput;
    private int selectedEnquiryInfo;
    private Enquiry enquiry;

    public EditEnquiryUI(Navigation navigation, Input getInput, int selectedEnquiryInfo, Enquiry enquiry) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedEnquiryInfo = selectedEnquiryInfo;
        this.enquiry = enquiry;
    }

    public void body() {

        EnquiryManager enquiryManager = EnquiryManager.getInstance();

        switch(selectedEnquiryInfo) {
            case 1:
                String title = getInput.getValidString("Edit title: ");
                if (title.equals(Dismiss.stringOption())) { return; }
                enquiry.setTitle(title);
                break;
            case 2:
                String content = getInput.getValidString("Edit content: ");
                if (content.equals(Dismiss.stringOption())) { return; }
                enquiry.setContent(content);
                break;
            case 3:
                if (getInput.confirmOrDiscard("Confirm changes?") != 1) { return; }
                enquiryManager.updateEnquiry(enquiry);
                LoadingIndicator.editingLoadingIndicator("enquiry");
                navigation.dismissView();

        }
    }
}
