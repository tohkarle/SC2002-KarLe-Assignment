package cams.view.suggestion;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.option.camp.CampInfoOptions;
import cams.utils.Dismiss;

public class CreateSuggestionView extends View {

    private CampManager campManager;
    
    // Options for this view:
    private CampInfoOptions createSuggestionOptions;

    // UIs in this view:
    private UI createSuggestionUI;

    public CreateSuggestionView(Navigation navigation, SuggestionManager suggestionManager, CampManager campManager) {
        super(navigation);
        this.campManager = campManager;
    }

    public void render() {

        createSuggestionOptions = (CampInfoOptions) super.getOptions("suggestion.CreateSuggestionOptions");
        createSuggestionOptions.updateCampInfo();
        createSuggestionOptions.display("Suggest edit, please choose the field you want to edit.");

        // Let user choose the field to edit
        int option = createSuggestionOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        campManager.setSelectedCampInfo(option);

        createSuggestionUI = super.getUI("suggestion.CreateSuggestionUI");
        createSuggestionUI.body();
    }
}
