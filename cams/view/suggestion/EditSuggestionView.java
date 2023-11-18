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
    private CampInfoOptions editSuggestionInfoOptions;

    // UIs for this view:
    private UI editSuggestionUI;

    public EditSuggestionView(Navigation navigation, CampManager campManager) {
        super(navigation);
        this.campManager = campManager;
    }

    public void render() {

        editSuggestionInfoOptions = (CampInfoOptions) super.getOptions("suggestion.EditSuggestionInfoOptions");

        // Update suggestion info to the latest
        editSuggestionInfoOptions.updateCampInfo();

        // Display suggestion info for editing
        editSuggestionInfoOptions.display("Select the field you want to edit: ");

        // Let user choose the field to edit
        int option = editSuggestionInfoOptions.selection();
        if (option == Dismiss.intOption()) { 
            campManager.clearTempCamp();
            super.getNavigation().dismissView();
            return; 
        }
        campManager.setSelectedCampInfo(option);

        editSuggestionUI = super.getUI("suggestion.EditSuggestionUI");
        editSuggestionUI.body();
    }
}
