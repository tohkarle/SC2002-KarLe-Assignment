package cams.option.enquiry;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableViewOnlyOptions;
import cams.model.Enquiry;


/**
 * Options object for viewing the Enquiry
 */
public class EnquiryInfoOptions extends DismissableViewOnlyOptions {

    private Enquiry enquiry;
    
    /**
     * Initialize this options object
     * @param enquiry
     */
    public EnquiryInfoOptions(Enquiry enquiry) {
        this.enquiry = enquiry;
        super.setOptions(
            new ArrayList<String>(Arrays.asList(
                String.format("Title: %s", enquiry.getTitle()),
                String.format("Content: %s", enquiry.getContent())
            ))
        );
    }


    /**
     * A method to retrieve the enquiry object
     * that the options are for
     * @return Enquiry object
     */
    public Enquiry getEnquiry() {
        return this.enquiry;
    }
}
