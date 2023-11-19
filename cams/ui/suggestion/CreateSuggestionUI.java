package cams.ui.suggestion;

import cams.components.LoadingIndicator;
import cams.components.option.Options;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.StringInput;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.model.Camp;
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
    
    private Options EditCampInfoOptions;
    private Navigation navigation;
    private UserManager userManager;
    private CampManager campManager;
    private SuggestionManager suggestionManager;
    private StringInput getString;
    private IntInput confirm;

    // Edit suggestion involves editing camp details
    private UI editCampNameUI;
    private UI editCampFacultyUI;
    private UI editCampLocationUI;
    private UI editCampDescriptionUI;
    private UI editCampVisibility;
    private UI editCampRegiatrationClosingDateUI;
    private UI editCampDatesUI;
    private UI editCampTotalSlotsUI;
    private UI editCampCommitteeSlotsUI;

    public CreateSuggestionUI(Navigation navigation, UserManager userManager, CampManager campManager, SuggestionManager suggestionManager, Options EditCampInfoOptions, StringInput getString, IntInput confirm) {
        this.navigation = navigation;
        this.EditCampInfoOptions = EditCampInfoOptions;
        this.userManager = userManager;
        this.campManager = campManager;
        this.suggestionManager = suggestionManager;
        this.getString = getString;
        this.confirm = confirm;
    }

    @Override
    public void body() {

        Camp tempCamp = campManager.getTempCamp();

        // Create and initialize all UIs for create suggestion
        editCampNameUI = new EditCampNameUI(tempCamp, EditCampInfoOptions.getOption(0));
        editCampFacultyUI = new EditCampFacultyUI(tempCamp, EditCampInfoOptions.getOption(1));
        editCampLocationUI = new EditCampLocationUI(tempCamp, EditCampInfoOptions.getOption(2));
        editCampDescriptionUI = new EditCampDescriptionUI(tempCamp, EditCampInfoOptions.getOption(3));
        editCampVisibility = new EditCampVisibilityUI(tempCamp, EditCampInfoOptions.getOption(4));
        editCampDatesUI = new EditCampDatesUI(tempCamp, EditCampInfoOptions.getOption(5), EditCampInfoOptions.getOption(6));
        editCampRegiatrationClosingDateUI = new EditCampRegiatrationClosingDateUI(tempCamp, EditCampInfoOptions.getOption(7));
        editCampTotalSlotsUI = new EditCampTotalSlotsUI(tempCamp, campManager, EditCampInfoOptions.getOption(8));
        editCampCommitteeSlotsUI = new EditCampCommitteeSlotsUI(tempCamp, campManager, EditCampInfoOptions.getOption(9));

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
        if (campManager.getSelectedCampInfo() <= editCampUIs.length) {
            editCampUIs[campManager.getSelectedCampInfo() - 1].body();
        } else if (campManager.getSelectedCampInfo() == editCampUIs.length + 1) {
            // Get title
            String title = getString.getValidString("Enter title (an overview of what you are suggesting): ");
            if (title.equals(Dismiss.stringOption())) {
                navigation.dismissView();
                return;
            }
            
            // Confirm changes and submit or discard
            if (confirm.getValidInt("Confirm changes and submit?") != 1) { return; }
            LoadingIndicator.submitLoadingIndicator("suggestion");
            suggestionManager.createSuggestion(userManager.getCurrentUser().getName(), title, campManager.getTempCamp());
            navigation.dismissView();
        }
    }
}
