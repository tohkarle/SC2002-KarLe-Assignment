package cams.ui.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.model.Camp;
import cams.option.camp.EditCampInfoOptions;
import cams.ui.camp.EditCampCommitteeSlotsUI;
import cams.ui.camp.EditCampDatesUI;
import cams.ui.camp.EditCampDescriptionUI;
import cams.ui.camp.EditCampFacultyUI;
import cams.ui.camp.EditCampLocationUI;
import cams.ui.camp.EditCampNameUI;
import cams.ui.camp.EditCampRegiatrationClosingDateUI;
import cams.ui.camp.EditCampTotalSlotsUI;
import cams.ui.camp.EditCampVisibilityUI;
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
        CampManager campManager = CampManager.getInstance();
        Options editCampInfoOptions = new EditCampInfoOptions();

        // Create and initialize all UIs for create suggestion
        UI[] editCampUIs = new UI[] {
            new EditCampNameUI(getInput, camp, editCampInfoOptions.getOption(0)),
            new EditCampNameUI(getInput, camp, editCampInfoOptions.getOption(0)),
            new EditCampFacultyUI(getInput, camp, editCampInfoOptions.getOption(1)),
            new EditCampLocationUI(getInput, camp, editCampInfoOptions.getOption(2)),
            new EditCampDescriptionUI(getInput, camp, editCampInfoOptions.getOption(3)),
            new EditCampVisibilityUI(camp, editCampInfoOptions.getOption(4)),
            new EditCampDatesUI(getInput, camp, editCampInfoOptions.getOption(5), editCampInfoOptions.getOption(6)),
            new EditCampRegiatrationClosingDateUI(getInput, camp, editCampInfoOptions.getOption(7)),
            new EditCampTotalSlotsUI(getInput, camp, campManager, editCampInfoOptions.getOption(8)),
            new EditCampCommitteeSlotsUI(getInput, camp, campManager, editCampInfoOptions.getOption(9)),
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
