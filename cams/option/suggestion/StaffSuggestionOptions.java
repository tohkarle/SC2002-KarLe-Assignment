package cams.option.suggestion;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableSelectableOptions;

public class StaffSuggestionOptions extends DismissableSelectableOptions {

    public StaffSuggestionOptions() {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Pending suggestions",
                "Approved suggestions",
                "Rejected suggestions"
            ))
        );
    }
}
