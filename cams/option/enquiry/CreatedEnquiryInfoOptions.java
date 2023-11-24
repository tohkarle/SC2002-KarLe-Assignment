package cams.option.enquiry;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Enquiry;

/**
 * Options object for editing an enquiry in the CreatedEnquiryInfoView
 */
public class CreatedEnquiryInfoOptions extends EnquiryInfoOptions {
    
    public CreatedEnquiryInfoOptions(Enquiry enquiry) {
        super(enquiry);
        this.changeOption();
    }
    
    public void changeOption() {
        if (!super.getEnquiry().getIsResolved()) {
            super.getOptions().addAll(Arrays.asList(
                    "(1) Edit enquiry",
                    "(2) Delete enquiry"
            ));
        } else {
            super.getOptions().addAll(Arrays.asList(
                String.format("Reply: %s", super.getEnquiry().getReply()),
                String.format("Resolved by: %s", super.getEnquiry().getResolvedBy())
            ));
        }
    }

    @Override
    public int selection() {
        IntInput selection = new GetSelectionWithDismiss(1, 2);
        return selection.getValidInt("Your selection: ");
    }
}
