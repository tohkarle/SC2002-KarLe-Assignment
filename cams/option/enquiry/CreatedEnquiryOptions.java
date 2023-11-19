package cams.option.enquiry;

import java.util.Arrays;

import cams.interfaces.IntInput;
import cams.manager.EnquiryManager;
import cams.ui.GetSelectionUI;

public class CreatedEnquiryOptions extends EnquiryInfoOptions {
    
    public CreatedEnquiryOptions(EnquiryManager enquiryManager) {
        super(enquiryManager);
    }

    @Override
    public void updateEnquiryInfo() {
        super.getEnquiryManager().createTempEnquiry();
        super.updateEnquiryInfo();
        super.getOptions().addAll(Arrays.asList(
                "(1) Edit enquiry",
                "(2) Delete enquiry"
        ));
    }

    @Override
    public int selection() {
        IntInput selection = new GetSelectionUI(-1, 2);
        return selection.getValidInt("Your selection: ");
    }
}
