package cams.option.report;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableSelectableOptions;

/**
 * Options object for filter selection of report generation in the GenerateEnquiryReportView
 */
public class GenerateEnquiryReportOptions extends DismissableSelectableOptions {
    /**
     * Initialize the report generation options
     */
    public GenerateEnquiryReportOptions() {
        super.setOptions(
            new ArrayList<String>(Arrays.asList(
                "Resolved enquiries only",
                "Unresolved enquiries only",
                "Both"
            ))
        );
    }
}
