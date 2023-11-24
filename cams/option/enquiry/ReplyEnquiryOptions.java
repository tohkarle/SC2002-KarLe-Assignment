package cams.option.enquiry;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Enquiry;

/**
 * Options object for replying to an enquiry in the ReplyEnquiryView
 */
public class ReplyEnquiryOptions extends EnquiryInfoOptions {

    /**
     * Initialize the ReplyEnquiry options
     * @param enquiry The enquiry to reply to
     */
    public ReplyEnquiryOptions(Enquiry enquiry) {
        super(enquiry);
        this.changeOption();
    }

    private void changeOption() {
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
            IntInput selection = new GetSelectionWithDismiss(1, 1);
            return selection.getValidInt("Your selection: ");
        }
    }
}
