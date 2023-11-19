package cams.ui.enquiry;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.StringInput;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;

public class ReplyEnquiryUI implements UI {

    private UserManager userManager;
    private EnquiryManager enquiryManager;
    private StringInput getString;
    private IntInput confirm;
    
    public ReplyEnquiryUI(UserManager userManager, EnquiryManager enquiryManager, StringInput getString, IntInput confirm) {
        this.userManager = userManager;
        this.enquiryManager = enquiryManager;
        this.getString = getString;
        this.confirm = confirm;
    }

    @Override
    public void body() {
        // Reply enquiry
        String reply = getString.getValidString("Enter a reply: ");
        if (reply.equals(Dismiss.stringOption())) { return; }

        // Confirm and submit reply or discard and go back
        if (confirm.getValidInt("Confirm submit reply?") != 1) { return; }

        // Update enquiry in 'database'
        enquiryManager.getTempEnquiry().setReply(reply);
        enquiryManager.getTempEnquiry().setResolvedBy(userManager.getCurrentUser().getName());
        enquiryManager.getTempEnquiry().setIsResolved(true);
        enquiryManager.updateEnquiry(enquiryManager.getTempEnquiry());
        LoadingIndicator.submitLoadingIndicator("reply");
    }
}
