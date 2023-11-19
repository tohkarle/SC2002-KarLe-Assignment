package cams.option.enquiry;

import java.util.Arrays;

import cams.interfaces.IntInput;
import cams.manager.EnquiryManager;
import cams.ui.GetSelectionUI;

public class ReplyEnquiryOptions extends EnquiryInfoOptions {

    public ReplyEnquiryOptions(EnquiryManager enquiryManager) {
        super(enquiryManager);
    }

    @Override
    public void updateEnquiryInfo() {
        super.getEnquiryManager().createTempEnquiry();
        super.updateEnquiryInfo();
        if (super.getEnquiryManager().getTempEnquiry().getReply() != null && super.getEnquiryManager().getTempEnquiry().getResolvedBy() != null && super.getEnquiryManager().getTempEnquiry().getIsResolved()) {
            super.getOptions().remove("(1) Reply enquiry");
            super.getOptions().addAll(Arrays.asList(
                String.format("Reply: %s", super.getEnquiryManager().getTempEnquiry().getReply()),
                String.format("Resolved by: %s", super.getEnquiryManager().getTempEnquiry().getResolvedBy())
            ));
        } else {
            super.getOptions().addAll(Arrays.asList(
                    "(1) Reply enquiry"
            ));
        }
    }

    @Override
    public int selection() {
        if (super.getEnquiryManager().getTempEnquiry().getReply() != null && super.getEnquiryManager().getTempEnquiry().getResolvedBy() != null && super.getEnquiryManager().getTempEnquiry().getIsResolved()) {
            return super.selection();
        } else {
            IntInput selection = new GetSelectionUI(-1, 1);
            return selection.getValidInt("Your selection: ");
        }
    }
}
