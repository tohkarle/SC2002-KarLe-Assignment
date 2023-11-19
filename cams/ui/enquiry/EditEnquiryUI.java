package cams.ui.enquiry;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.StringInput;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.utils.Dismiss;

public class EditEnquiryUI implements UI {

    private Navigation navigation;
    private EnquiryManager enquiryManager;
    private StringInput getString;
    private IntInput confirm;

    public EditEnquiryUI(Navigation navigation, EnquiryManager enquiryManager, StringInput getString, IntInput confirm) {
        this.navigation = navigation;
        this.enquiryManager = enquiryManager;
        this.getString = getString;
        this.confirm = confirm;
    }

    public void body() {
        switch(enquiryManager.getSelectedEnquiryInfo()) {
            case 1:
                String title = getString.getValidString("Edit title: ");
                if (title.equals(Dismiss.stringOption())) { return; }
                enquiryManager.getTempEnquiry().setTitle(title);
                break;
            case 2:
                String content = getString.getValidString("Edit content: ");
                if (content.equals(Dismiss.stringOption())) { return; }
                enquiryManager.getTempEnquiry().setContent(content);
                break;
            case 3:
                if (confirm.getValidInt("Confirm changes?") != 1) { return; }
                enquiryManager.updateEnquiry(enquiryManager.getTempEnquiry());
                LoadingIndicator.editingLoadingIndicator("enquiry");
                navigation.dismissView();

        }
    }
}
