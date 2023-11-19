package cams.view.enquiry;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.EnquiryManager;
import cams.option.enquiry.EnquiryInfoOptions;
import cams.utils.Dismiss;

public class EditEnquiryView extends View {

    private EnquiryManager enquiryManager;

    // Options for this view:
    private EnquiryInfoOptions editEnquiryOptions;

    // UIs for this view:
    private UI editEnquiryUI;

    public EditEnquiryView(Navigation navigation, EnquiryManager enquiryManager) {
        super(navigation);
        this.enquiryManager = enquiryManager;
    }

    public void render() {

        editEnquiryOptions = (EnquiryInfoOptions) super.getOptions("enquiry.EditEnquiryOptions");

        // Update enquiry info to the latest
        editEnquiryOptions.updateEnquiryInfo();

        // Display enquiry info for editing
        editEnquiryOptions.display("Select the field you want to edit: ");

        // Let user choose the field to edit
        int option = editEnquiryOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        enquiryManager.setSelectedEnquiryInfo(option);

        editEnquiryUI = super.getUI("enquiry.EditEnquiryUI");
        editEnquiryUI.body();
    }
}
