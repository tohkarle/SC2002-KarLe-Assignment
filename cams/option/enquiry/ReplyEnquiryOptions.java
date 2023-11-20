package cams.option.enquiry;

import java.util.Arrays;

import cams.interfaces.IntInput;
import cams.model.Enquiry;
import cams.ui.GetSelectionUI;

public class ReplyEnquiryOptions extends EnquiryInfoOptions {

    public ReplyEnquiryOptions(Enquiry enquiry) {
        super(enquiry);
        this.changeOption();
    }

    public void changeOption() {
        if (super.getEnquiry().getReply() != null && super.getEnquiry().getResolvedBy() != null && super.getEnquiry().getIsResolved()) {
            super.getOptions().remove("(1) Reply enquiry");
            super.getOptions().addAll(Arrays.asList(
                String.format("Reply: %s", super.getEnquiry().getReply()),
                String.format("Resolved by: %s", super.getEnquiry().getResolvedBy())
            ));
        } else {
            super.getOptions().addAll(Arrays.asList(
                    "(1) Reply enquiry"
            ));
        }
    }

    @Override
    public int selection() {
        if (super.getEnquiry().getReply() != null && super.getEnquiry().getResolvedBy() != null && super.getEnquiry().getIsResolved()) {
            return super.selection();
        } else {
            IntInput selection = new GetSelectionUI(-1, 1);
            return selection.getValidInt("Your selection: ");
        }
    }
}
