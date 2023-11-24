package cams.option.enquiry;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableSelectableOptions;

/**
 * Options object for selecting status of enquiries to view in the EnquiryStatusView
 * i.e. pending or resolved enquiries
 */
public class EnquiryStatusOptions extends DismissableSelectableOptions {

    public EnquiryStatusOptions() {

        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Pending enquiries",
                "Resolved enquiries"
            ))
        );
    }
}
