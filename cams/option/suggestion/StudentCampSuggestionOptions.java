package cams.option.suggestion;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.Page;

public class StudentCampSuggestionOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> suggestionIDs;
    private UserManager userManager;
    private CampManager campManager;
    private SuggestionManager suggestionManager;

    public StudentCampSuggestionOptions(UserManager userManager, CampManager campManager, SuggestionManager suggestionManager) {
        this.userManager = userManager;
        this.campManager = campManager;
        this.suggestionManager = suggestionManager;
        this.fetchStudentCampSuggestions();
    }

    @Override
    public void display(String title) {
        fetchStudentCampSuggestions();
        if (super.getOptionsSize() == 0) {
            Page.header(this.noCampTitle);
        } else {
            super.display(title);
        }
    }

    @Override
    public int selection() {
        int option = super.selection();
        if (option == Dismiss.intOption()) { return option; }
        return this.suggestionIDs.get(option - 1);
    }

    private void fetchStudentCampSuggestions() {
        // Fetch student's suggestions for this camp
        this.noCampTitle = "You have not raised any suggestion under this camp. Please raise suggestion under 'Raise Suggestion'.";
        super.setOptions(suggestionManager.getAllStudentCampSuggestionTitles(userManager.getCurrentUser().getName(), campManager.getSelectedID()));
        this.suggestionIDs = suggestionManager.getAllStudentCampSuggestionIDs(userManager.getCurrentUser().getName(), campManager.getSelectedID());
    }
}
