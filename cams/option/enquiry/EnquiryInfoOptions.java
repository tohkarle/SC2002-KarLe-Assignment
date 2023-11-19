package cams.option.enquiry;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableViewOnlyOptions;
import cams.manager.EnquiryManager;

public class EnquiryInfoOptions extends DismissableViewOnlyOptions {

    private EnquiryManager enquiryManager;
    
    public EnquiryInfoOptions(EnquiryManager enquiryManager) {
        this.enquiryManager = enquiryManager;
    }

    public void updateEnquiryInfo() {
        super.setOptions(
            new ArrayList<String>(Arrays.asList(
                String.format("Title: %s", enquiryManager.getTempEnquiry().getTitle()),
                String.format("Content: %s", enquiryManager.getTempEnquiry().getContent())
            ))
        );
    }

    public EnquiryManager getEnquiryManager() {
        return this.enquiryManager;
    }
}
