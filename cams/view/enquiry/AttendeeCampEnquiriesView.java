package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.EnquiryManager;
import cams.utils.Dismiss;

public class AttendeeCampEnquiriesView extends View {

    private EnquiryManager enquiryManager;

    // Options for this view:
    private Options AttendeeCampEnquiriesOptions;

    public AttendeeCampEnquiriesView(Navigation navigation, EnquiryManager enquiryManager) {
        super(navigation);
        this.enquiryManager = enquiryManager;
    }

    public void render() {
        // Display enquiries
        AttendeeCampEnquiriesOptions = super.getOptions("enquiry.AttendeeCampEnquiriesOptions");
        AttendeeCampEnquiriesOptions.display("Select enquiry to view details:");

        // Let user select enquiry to view details
        int option = AttendeeCampEnquiriesOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        enquiryManager.setSelectedEnquiryID(option);

        // Navigate to CreatedEnquiryInfoView
        super.getNavigation().navigateTo("enquiry.CreatedEnquiryInfoView");
    }
}
