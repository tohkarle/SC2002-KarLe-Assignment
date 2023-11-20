package cams.option.enquiry;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Enquiry;
import cams.utils.Page;

public class EditEnquiryOptions extends EnquiryInfoOptions {

    public EditEnquiryOptions(Enquiry enquiry) {
        super(enquiry);
        this.changeOption();
    }

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
