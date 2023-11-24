package cams.option.suggestion;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableSelectableOptions;

/**
 * Options object for selecting status of suggestions to view in the SuggestionStatusView
 * i.e. pending, approved or rejected suggestions
 */
public class SuggestionStatusOptions extends DismissableSelectableOptions {

    /**
     * Initialize the suggestion status options
     */
    public SuggestionStatusOptions() {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Pending suggestions",
                "Approved suggestions",
                "Rejected suggestions"
            ))
        );
    }
}
