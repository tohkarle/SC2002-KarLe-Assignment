package cams.option.report;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableSelectableOptions;

/**
 * Options object for filter selection of report generation in the GenerateRegistrationReportView
 */
public class GenerateReportOptions extends DismissableSelectableOptions {

    /**
     * Initialize the report generation options
     */
    public GenerateReportOptions() {
        super.setOptions(
            new ArrayList<String>(Arrays.asList(
                "Attendees only",
                "Committee only",
                "Both"
            ))
        );
    }
}
