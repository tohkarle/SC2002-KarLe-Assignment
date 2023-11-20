package cams.option.enquiry;

import java.util.Arrays;

import cams.interfaces.IntInput;
import cams.model.Enquiry;
import cams.ui.GetSelectionUI;

public class CreatedEnquiryOptions extends EnquiryInfoOptions {
    
    public CreatedEnquiryOptions(Enquiry enquiry) {
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
        IntInput selection = new GetSelectionUI(-1, 2);
        return selection.getValidInt("Your selection: ");
    }
}
