package cams.ui.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.model.Camp;
import cams.option.camp.EditCampInfoOptions;
import cams.ui.camp.CampCommitteeSlotsUI;
import cams.ui.camp.CampDatesUI;
import cams.ui.camp.CampDescriptionUI;
import cams.ui.camp.CampFacultyUI;
import cams.ui.camp.CampLocationUI;
import cams.ui.camp.CampNameUI;
import cams.ui.camp.CampRegiatrationClosingDateUI;
import cams.ui.camp.CampTotalSlotsUI;
import cams.ui.camp.CampVisibilityUI;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;

public class CreateSuggestionUI implements UI {

    private Navigation navigation;
    private Input getInput;
    private int selectedCampInfo;
    private Camp camp;

    public CreateSuggestionUI(Navigation navigation, Input getInput, int selectedCampInfo, Camp camp) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedCampInfo = selectedCampInfo;
        this.camp = camp;
    }

    @Override
    public void body() {

        UserManager userManager = UserManager.getInstance();
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        Options editCampInfoOptions = new EditCampInfoOptions();

        // Create and initialize all UIs for create suggestion
        UI[] editCampUIs = new UI[] {
            new CampNameUI(getInput, camp, editCampInfoOptions.getOption(0)),
            new CampFacultyUI(getInput, camp, editCampInfoOptions.getOption(1)),
            new CampLocationUI(getInput, camp, editCampInfoOptions.getOption(2)),
            new CampDescriptionUI(getInput, camp, editCampInfoOptions.getOption(3)),
            new CampVisibilityUI(camp, editCampInfoOptions.getOption(4)),
            new CampDatesUI(getInput, camp, editCampInfoOptions.getOption(5), editCampInfoOptions.getOption(6)),
            new CampRegiatrationClosingDateUI(getInput, camp, editCampInfoOptions.getOption(7)),
            new CampTotalSlotsUI(getInput, camp, editCampInfoOptions.getOption(8)),
            new CampCommitteeSlotsUI(getInput, camp, editCampInfoOptions.getOption(9)),
        };

        // Edit camp details
        if (selectedCampInfo <= editCampUIs.length) {
            editCampUIs[selectedCampInfo - 1].body();
        } else if (selectedCampInfo == editCampUIs.length + 1) {
            // Get title
            String title = getInput.getValidString("Enter title (an overview of what you are suggesting): ");
            if (title.equals(Dismiss.stringOption())) { return; }
            
            // Confirm changes and submit or discard
            if (getInput.confirmOrDiscard("Confirm changes and submit?") != 1) { return; }
            LoadingIndicator.submitLoadingIndicator("suggestion");
            suggestionManager.createSuggestion(userManager.getCurrentUser().getName(), title, camp);
            navigation.dismissView();
        }
    }
}
