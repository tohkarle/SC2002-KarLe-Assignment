package cams.option.enquiry;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableViewOnlyOptions;
import cams.manager.EnquiryManager;
import cams.model.Enquiry;

public class EnquiryInfoOptions extends DismissableViewOnlyOptions {

    private EnquiryManager enquiryManager;
    private Enquiry enquiry;
    
    public EnquiryInfoOptions(EnquiryManager enquiryManager) {
        this.enquiryManager = enquiryManager;
    }

    public void updateEnquiryInfo() {
        enquiryManager.createTempEnquiry();
        this.enquiry = enquiryManager.getTempEnquiry();
        this.setEnquiryInfo();
    }

    public void setEnquiryInfo() {
        super.setOptions(
            new ArrayList<String>(Arrays.asList(
                String.format("Title: %s", this.enquiry.getTitle()),
                String.format("Content: %s", this.enquiry.getContent())
            ))
        );
    }

    public EnquiryManager getEnquiryManager() {
        return this.enquiryManager;
    }

    public Enquiry getEnquiry() {
        return this.enquiry;
    }

    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }
}
