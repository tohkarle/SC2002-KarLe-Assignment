package cams.option.enquiry;

import java.util.Arrays;

import cams.interfaces.IntInput;
import cams.manager.EnquiryManager;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Page;

public class EditEnquiryOptions extends EnquiryInfoOptions {
    public EditEnquiryOptions(EnquiryManager enquiryManager) {
        super(enquiryManager);
    }

    @Override
    public void updateEnquiryInfo() {
        super.updateEnquiryInfo();
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
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
