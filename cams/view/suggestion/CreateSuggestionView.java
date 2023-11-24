package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.model.Camp;
import cams.option.suggestion.CreateSuggestionOptions;
import cams.ui.suggestion.CreateSuggestionUI;
import cams.utils.Dismiss;

/**
 * View object for Create Suggestion page
 */
public class CreateSuggestionView implements View {

    private Navigation navigation;
    private Input getInput;
    private Camp camp;

    /**
     * Initialize the CreateSuggestionView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param camp Camp object to be edited
     */
    public CreateSuggestionView(Navigation navigation, Input getInput, Camp camp) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.camp = camp;
    }

    /**
     * Render the CreateSuggestionView
     */
    public void render() {

        Options createSuggestionOptions = new CreateSuggestionOptions(camp);

        createSuggestionOptions.display("Suggest edit, please choose the field you want to edit.");

        // Let user choose the field to edit
        int selectedCampInfo = createSuggestionOptions.selection();
        if (selectedCampInfo == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        UI createSuggestionUI = new CreateSuggestionUI(navigation, getInput, selectedCampInfo, camp);
        createSuggestionUI.body();
    }
}
