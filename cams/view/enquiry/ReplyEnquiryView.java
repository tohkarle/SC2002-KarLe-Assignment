package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.EnquiryManager;
import cams.model.Enquiry;
import cams.option.enquiry.ReplyEnquiryOptions;
import cams.ui.enquiry.ReplyEnquiryUI;
import cams.utils.Dismiss;

public class ReplyEnquiryView implements View {

    private Navigation navigation;
    private int selectedEnquiryID;

    public ReplyEnquiryView(Navigation navigation, int selectedEnquiryID) {
        this.navigation = navigation;
        this.selectedEnquiryID = selectedEnquiryID;
    }

    public void render() {
        EnquiryManager enquiryManager = EnquiryManager.getInstance();
        Enquiry enquiry = enquiryManager.getEnquiry(selectedEnquiryID);
        Options replyEnquiryOptions = new ReplyEnquiryOptions(enquiry);

        // Display enquiry details
        replyEnquiryOptions.display("Enquiry details: ");

        // Let user choose to go back or reply enquiry
        if (replyEnquiryOptions.selection() == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Reply enquiry
        UI replyEnquiryUI = new ReplyEnquiryUI(enquiry);
        replyEnquiryUI.body();
    }
}
