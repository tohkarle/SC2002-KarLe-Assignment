package cams.view.suggestion;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.option.camp.CampInfoOptions;
import cams.utils.Dismiss;

public class EditSuggestionView extends View {

    private CampManager campManager;

    // Options for this view:
    private CampInfoOptions editSuggestionOptions;

    // UIs for this view:
    private UI editSuggestionUI;

    public EditSuggestionView(Navigation navigation, CampManager campManager) {
        super(navigation);
        this.campManager = campManager;
    }

    public void render() {

        editSuggestionOptions = (CampInfoOptions) super.getOptions("suggestion.EditSuggestionOptions");

        // Update suggestion info to the latest
        editSuggestionOptions.updateCampInfo();

        // Display suggestion info for editing
        editSuggestionOptions.display("Select the field you want to edit: ");

        // Let user choose the field to edit
        int option = editSuggestionOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        campManager.setSelectedCampInfo(option);

        editSuggestionUI = super.getUI("suggestion.EditSuggestionUI");
        editSuggestionUI.body();
    }
}
