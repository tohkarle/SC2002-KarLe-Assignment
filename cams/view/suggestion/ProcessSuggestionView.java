package cams.view.suggestion;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.SuggestionManager;
import cams.model.SuggestionStatus;
import cams.option.camp.CampInfoOptions;
import cams.utils.Dismiss;

public class ProcessSuggestionView extends View {

    private SuggestionManager suggestionManager;

    // Options for this view:
    private CampInfoOptions processSuggestionOptions;

    // UIs for this view:
    private UI processSuggestionUI;

    public ProcessSuggestionView(Navigation navigation, SuggestionManager suggestionManager) {
        super(navigation);
        this.suggestionManager = suggestionManager;
    }

    public void render() {

        processSuggestionOptions = (CampInfoOptions) super.getOptions("suggestion.ProcessSuggestionOptions");

        // Update suggestion details to latest
        processSuggestionOptions.updateCampInfo();

        // Display suggestion details
        processSuggestionOptions.viewOnly("Suggestion details: ");

        if (suggestionManager.getSelectedSuggestionStatus() == SuggestionStatus.PENDING) {
            // Allo process suggestion if suggestion is pending
            processSuggestionUI = super.getUI("suggestion.ProcessSuggestionUI");
            processSuggestionUI.body();
        } else {
            // Let staff view details and go back only
            if (processSuggestionOptions.dismiss() == Dismiss.intOption()) {
                super.getNavigation().dismissView();
            }
        }
    }
}
