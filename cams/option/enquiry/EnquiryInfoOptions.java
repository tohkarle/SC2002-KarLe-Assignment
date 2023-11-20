package cams.option.enquiry;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableViewOnlyOptions;
import cams.model.Enquiry;

public class EnquiryInfoOptions extends DismissableViewOnlyOptions {

    private Enquiry enquiry;
    
    public EnquiryInfoOptions(Enquiry enquiry) {
        this.enquiry = enquiry;
        super.setOptions(
            new ArrayList<String>(Arrays.asList(
                String.format("Title: %s", enquiry.getTitle()),
                String.format("Content: %s", enquiry.getContent())
            ))
        );
    }

    public Enquiry getEnquiry() {
        return this.enquiry;
    }
}
