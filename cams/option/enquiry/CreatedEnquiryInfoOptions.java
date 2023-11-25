package cams.option.enquiry;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Enquiry;

/**
 * Options object for editing an enquiry in the CreatedEnquiryInfoView
 */
public class CreatedEnquiryInfoOptions extends EnquiryInfoOptions {
    

    /**
     * Initialize this option object
     */
    public CreatedEnquiryInfoOptions(Enquiry enquiry) {
        super(enquiry);
        this.changeOption();
    }
    
    /**
     * A method to switch between option types
     */
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


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    @Override
    public int selection() {
        IntInput selection = new GetSelectionWithDismiss(1, 2);
        return selection.getValidInt("Your selection: ");
    }
}
