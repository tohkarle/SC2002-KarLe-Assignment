package cams.ui.enquiry;

import cams.components.LoadingIndicator;
import cams.components.input.GetStringInput;
import cams.interfaces.IntInput;
import cams.interfaces.StringInput;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.manager.UserManager;
import cams.model.Enquiry;
import cams.ui.ConfirmOrDiscardUI;
import cams.utils.Dismiss;

public class ReplyEnquiryUI implements UI {

    private Enquiry enquiry;

    public ReplyEnquiryUI(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    @Override
    public void body() {
        UserManager userManager = UserManager.getInstance();
        EnquiryManager enquiryManager = EnquiryManager.getInstance();
        StringInput getString = new GetStringInput();
        IntInput confirm = new ConfirmOrDiscardUI();

        // Reply enquiry
        String reply = getString.getValidString("Enter a reply: ");
        if (reply.equals(Dismiss.stringOption())) { return; }

        // Confirm and submit reply or discard and go back
        if (confirm.getValidInt("Confirm submit reply?") != 1) { return; }

        // Update enquiry in 'database'
        enquiry.setReply(reply);
        enquiry.setResolvedBy(userManager.getCurrentUser().getName());
        enquiry.setIsResolved(true);
        enquiryManager.updateEnquiry(enquiry);
        LoadingIndicator.submitLoadingIndicator("reply");
    }
}
