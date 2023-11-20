package cams.ui.enquiry;

import cams.components.input.ConfirmOrDiscard;
import cams.components.input.GetStringInput;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.StringInput;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.model.Enquiry;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;

public class EditEnquiryUI implements UI {

    private Navigation navigation;
    private int selectedEnquiryInfo;
    private Enquiry enquiry;

    public EditEnquiryUI(Navigation navigation, int selectedEnquiryInfo, Enquiry enquiry) {
        this.navigation = navigation;
        this.selectedEnquiryInfo = selectedEnquiryInfo;
        this.enquiry = enquiry;
    }

    public void body() {

        EnquiryManager enquiryManager = EnquiryManager.getInstance();
        StringInput getString = new GetStringInput();
        IntInput confirm = new ConfirmOrDiscard();

        switch(selectedEnquiryInfo) {
            case 1:
                String title = getString.getValidString("Edit title: ");
                if (title.equals(Dismiss.stringOption())) { return; }
                enquiry.setTitle(title);
                break;
            case 2:
                String content = getString.getValidString("Edit content: ");
                if (content.equals(Dismiss.stringOption())) { return; }
                enquiry.setContent(content);
                break;
            case 3:
                if (confirm.getValidInt("Confirm changes?") != 1) { return; }
                enquiryManager.updateEnquiry(enquiry);
                LoadingIndicator.editingLoadingIndicator("enquiry");
                navigation.dismissView();

        }
    }
}
