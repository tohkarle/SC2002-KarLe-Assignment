package cams.ui.suggestion;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Suggestion;
import cams.utils.Dismiss;

/**
 * UI object for editing the title of a suggestion
 */
public class EditSuggestionTitleUI implements UI {

    private Input getInput;
    private Suggestion suggestion;
    private String title;
    
    /**
     * Initialize the EditSuggestionTitleUI
     * @param getInput Input object used to get user input
     * @param suggestion Suggestion object to be edited
     * @param title Title of the suggestion
     */
    public EditSuggestionTitleUI(Input getInput, Suggestion suggestion, String title) {
        this.getInput = getInput;
        this.suggestion = suggestion;
        this.title = title;
    }

    /**
     * Executes user interaction logic for editing the title of a suggestion
     */
    @Override
    public void body() {
        String title = getInput.getValidString(this.title);
        if (title.equals(Dismiss.stringOption())) { return; }
        suggestion.setTitle(title);
    }
}
