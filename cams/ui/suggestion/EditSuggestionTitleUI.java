package cams.ui.suggestion;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Suggestion;
import cams.utils.Dismiss;

public class EditSuggestionTitleUI implements UI {

    private Input getInput;
    private Suggestion suggestion;
    private String title;
    
    public EditSuggestionTitleUI(Input getInput, Suggestion suggestion, String title) {
        this.getInput = getInput;
        this.suggestion = suggestion;
        this.title = title;
    }

    @Override
    public void body() {
        String title = getInput.getValidString(this.title);
        if (title.equals(Dismiss.stringOption())) { return; }
        suggestion.setTitle(title);
    }
}
