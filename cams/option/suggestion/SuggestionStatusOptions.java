package cams.option.suggestion;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableSelectableOptions;

public class SuggestionStatusOptions extends DismissableSelectableOptions {

    public SuggestionStatusOptions() {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Pending suggestions",
                "Approved suggestions",
                "Rejected suggestions"
            ))
        );
    }
}
