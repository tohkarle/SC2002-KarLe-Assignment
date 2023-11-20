package cams.option.report;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableSelectableOptions;

public class GenerateReportOptions extends DismissableSelectableOptions {
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
