package cams.view.enquiry;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.enquiry.EnquiryInfoOptions;
import cams.utils.Dismiss;

public class CreatedEnquiryInfoView extends View {

    // Options for this view:
    private EnquiryInfoOptions createdSuggestionOptions;

    // UIs for this view:
    private UI deleteEnquiryUI;

    public CreatedEnquiryInfoView(Navigation navigation) {
        super(navigation);
    }

    public void render() {

        createdSuggestionOptions = (EnquiryInfoOptions) super.getOptions("enquiry.CreatedEnquiryOptions");

        // Update created enquiry details to latest
        createdSuggestionOptions.updateEnquiryInfo();

        // Display created enquiry details
        createdSuggestionOptions.display("Enquiry details: ");

        // Allow student to go back or edit enquiry and delete enquiry
        int option = createdSuggestionOptions.selection();
        if (option == Dismiss.intOption() ) { 
            super.getNavigation().dismissView();
            return; 
        }

        switch (option) {
            case 1:
                super.getNavigation().navigateTo("enquiry.EditEnquiryView");
                break;
            case 2:
                deleteEnquiryUI = super.getUI("enquiry.DeleteEnquiryUI");
                deleteEnquiryUI.body();
                break;
        }
    }
}
