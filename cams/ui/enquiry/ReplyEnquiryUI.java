package cams.ui.enquiry;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.manager.UserManager;
import cams.model.Enquiry;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;

public class ReplyEnquiryUI implements UI {

    private Input getInput;
    private Enquiry enquiry;

    public ReplyEnquiryUI(Input getInput, Enquiry enquiry) {
        this.getInput = getInput;
        this.enquiry = enquiry;
    }

    @Override
    public void body() {
        UserManager userManager = UserManager.getInstance();
        EnquiryManager enquiryManager = EnquiryManager.getInstance();

        // Reply enquiry
        String reply = getInput.getValidString("Enter a reply: ");
        if (reply.equals(Dismiss.stringOption())) { return; }

        // Confirm and submit reply or discard and go back
        if (getInput.confirmOrDiscard("Confirm submit reply?") != 1) { return; }

        // Update enquiry in 'database'
        enquiry.setReply(reply);
        enquiry.setResolvedBy(userManager.getCurrentUser().getName());
        enquiry.setIsResolved(true);
        enquiryManager.updateEnquiry(enquiry);
        LoadingIndicator.submitLoadingIndicator("reply");
    }
}
