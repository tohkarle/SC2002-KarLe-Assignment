package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.EnquiryManager;
import cams.utils.Dismiss;

public class CampEnquiriesView extends View {

    private EnquiryManager enquiryManager;

    // Options for this view:
    private Options campEnquiryOptions;

    public CampEnquiriesView(Navigation navigation, EnquiryManager enquiryManager) {
        super(navigation);
        this.enquiryManager  = enquiryManager;
    }

    public void render() {
        // Display enquiry
        campEnquiryOptions = super.getOptions("enquiry.CampEnquiryOptions");
        campEnquiryOptions.display("Select enquiry to view details:");

        // Let user select enquiry to view details
        int option = campEnquiryOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        enquiryManager.setSelectedEnquiryID(option);

        // Navigate to ReplyEnquiryView
        super.getNavigation().navigateTo("enquiry.ReplyEnquiryView");
    }
}
