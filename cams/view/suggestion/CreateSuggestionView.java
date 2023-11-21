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

public class CreateSuggestionView implements View {

    private Navigation navigation;
    private Input getInput;
    private Camp camp;

    public CreateSuggestionView(Navigation navigation, Input getInput, Camp camp) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.camp = camp;
    }

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
