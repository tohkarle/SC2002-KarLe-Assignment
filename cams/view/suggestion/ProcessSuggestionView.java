package cams.view.suggestion;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.CampInfoOptions;

public class ProcessSuggestionView extends View {

    // Options for this view:
    private CampInfoOptions processSuggestionOptions;

    // UIs for this view:
    private UI processSuggestionUI;

    public ProcessSuggestionView(Navigation navigation) {
        super(navigation);
    }

    public void render() {

        processSuggestionOptions = (CampInfoOptions) super.getOptions("suggestion.ProcessSuggestionOptions");

        // Update suggestion details to latest
        processSuggestionOptions.updateCampInfo();

        // Display suggestion details
        processSuggestionOptions.viewOnly("Suggestion details: ");

        // Process suggestion
        processSuggestionUI = super.getUI("suggestion.ProcessSuggestionUI");
        processSuggestionUI.body();
    }
}
