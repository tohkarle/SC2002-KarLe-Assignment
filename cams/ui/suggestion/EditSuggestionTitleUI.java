package cams.ui.suggestion;

import cams.components.input.GetStringInput;
import cams.interfaces.UI;
import cams.model.Suggestion;
import cams.utils.Dismiss;

public class EditSuggestionTitleUI extends GetStringInput implements UI {

    private Suggestion suggestion;
    private String title;
    
    public EditSuggestionTitleUI(Suggestion suggestion, String title) {
        this.suggestion = suggestion;
        this.title = title;
    }

    @Override
    public void body() {
        String title = super.getValidString(this.title);
        if (title.equals(Dismiss.stringOption())) { return; }
        suggestion.setTitle(title);
    }
}
