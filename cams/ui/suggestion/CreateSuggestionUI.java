package cams.ui.suggestion;

import cams.components.LoadingIndicator;
import cams.components.input.GetStringInput;
import cams.components.option.Options;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.StringInput;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.model.Camp;
import cams.option.camp.EditCampInfoOptions;
import cams.ui.ConfirmOrDiscardUI;
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

public class CreateSuggestionUI implements UI {

    private Navigation navigation;
    private int selectedCampInfo;
    private Camp camp;

    public CreateSuggestionUI(Navigation navigation, int selectedCampInfo, Camp camp) {
        this.navigation = navigation;
        this.selectedCampInfo = selectedCampInfo;
        this.camp = camp;
    }

    @Override
    public void body() {

        UserManager userManager = UserManager.getInstance();
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        CampManager campManager = CampManager.getInstance();
        StringInput getString = new GetStringInput();
        IntInput confirm = new ConfirmOrDiscardUI();
        Options editCampInfoOptions = new EditCampInfoOptions();

        // Create and initialize all UIs for create suggestion
        UI editCampNameUI = new EditCampNameUI(camp, editCampInfoOptions.getOption(0));
        UI editCampFacultyUI = new EditCampFacultyUI(camp, editCampInfoOptions.getOption(1));
        UI editCampLocationUI = new EditCampLocationUI(camp, editCampInfoOptions.getOption(2));
        UI editCampDescriptionUI = new EditCampDescriptionUI(camp, editCampInfoOptions.getOption(3));
        UI editCampVisibility = new EditCampVisibilityUI(camp, editCampInfoOptions.getOption(4));
        UI editCampDatesUI = new EditCampDatesUI(camp, editCampInfoOptions.getOption(5), editCampInfoOptions.getOption(6));
        UI editCampRegiatrationClosingDateUI = new EditCampRegiatrationClosingDateUI(camp, editCampInfoOptions.getOption(7));
        UI editCampTotalSlotsUI = new EditCampTotalSlotsUI(camp, campManager, editCampInfoOptions.getOption(8));
        UI editCampCommitteeSlotsUI = new EditCampCommitteeSlotsUI(camp, campManager, editCampInfoOptions.getOption(9));

        UI[] editCampUIs = new UI[] {
            editCampNameUI,
            editCampFacultyUI,
            editCampLocationUI,
            editCampDescriptionUI,
            editCampVisibility,
            editCampDatesUI,
            editCampRegiatrationClosingDateUI,
            editCampTotalSlotsUI,
            editCampCommitteeSlotsUI
        };

        // Edit camp details
        if (selectedCampInfo <= editCampUIs.length) {
            editCampUIs[selectedCampInfo - 1].body();
        } else if (selectedCampInfo == editCampUIs.length + 1) {
            // Get title
            String title = getString.getValidString("Enter title (an overview of what you are suggesting): ");
            if (title.equals(Dismiss.stringOption())) { return; }
            
            // Confirm changes and submit or discard
            if (confirm.getValidInt("Confirm changes and submit?") != 1) { return; }
            LoadingIndicator.submitLoadingIndicator("suggestion");
            suggestionManager.createSuggestion(userManager.getCurrentUser().getName(), title, camp);
            navigation.dismissView();
        }
    }
}
