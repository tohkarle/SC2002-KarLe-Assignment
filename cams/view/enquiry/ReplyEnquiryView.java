package cams.view.enquiry;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.enquiry.EnquiryInfoOptions;
import cams.utils.Dismiss;

public class ReplyEnquiryView extends View {

    // Options for this view:
    private EnquiryInfoOptions replyEnquiryOptions;

    // UIs for this view:
    private UI replyEnquiryUI;

    public ReplyEnquiryView(Navigation navigation) {
        super(navigation);
    }

    public void render() {

        replyEnquiryOptions = (EnquiryInfoOptions) super.getOptions("enquiry.ReplyEnquiryOptions");

        // Update enquiry details to latest
        replyEnquiryOptions.updateEnquiryInfo();

        // Display enquiry details
        replyEnquiryOptions.display("Enquiry details: ");

        // Let user choose to go back or reply enquiry
        if (replyEnquiryOptions.selection() == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }

        // Reply enquiry
        replyEnquiryUI = super.getUI("enquiry.ReplyEnquiryUI");
        replyEnquiryUI.body();
    }
}
