package cams.view.suggestion;

import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.CampInfoOptions;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Dismiss;

public class CreatedSuggestionInfoView extends View {

    // Options for this view:
    private CampInfoOptions createdSuggestionOptions;

    // UIs for this view:
    private UI deleteSuggestionUI;

    public CreatedSuggestionInfoView(Navigation navigation) {
        super(navigation);
    }

    public void render() {

        createdSuggestionOptions = (CampInfoOptions) super.getOptions("suggestion.CreatedSuggestionOptions");

        // Update created suggestion details to latest
        createdSuggestionOptions.updateCampInfo();

        // Display created suggestion details
        createdSuggestionOptions.viewOnly("Suggestion details: ");

        // Allow student to go back or edit suggestion and delete suggestion
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(-1, 2);
        int option = selectionWithDismiss.getValidInt("Your selection: ");
        if (option == Dismiss.intOption() ) { 
            super.getNavigation().dismissView();
            return; 
        }

        switch (option) {
            case 1:
                super.getNavigation().navigateTo("suggestion.EditSuggestionView");
                break;
            case 2:
                deleteSuggestionUI = super.getUI("suggestion.DeleteSuggestionUI");
                deleteSuggestionUI.body();
                break;
        }
    }
}
