package cams.option.enquiry;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Enquiry;
import cams.utils.Page;

/**
 * Options object for managing edits to an enquiry in the EditEnquiryView
 */
public class EditEnquiryOptions extends EnquiryInfoOptions {

    /**
     * Initialize the EditEnquiry options
     * @param enquiry The enquiry to edit
     */
    public EditEnquiryOptions(Enquiry enquiry) {
        super(enquiry);
        this.changeOption();
    }

    /**
     * Change the options to add the update changes option
     */
    public void changeOption() {
        super.getOptions().addAll(Arrays.asList(
                "Update changes"
        ));
    }

    @Override
    public void display(String title) {
        Page.header(title);
        super.printOptions();
    }

    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
