package cams.option.suggestion;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.model.SuggestionStatus;
import cams.utils.Dismiss;
import cams.utils.Page;

public class CommitteeCampSuggestionsOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> suggestionIDs;
    private UserManager userManager;
    private CampManager campManager;
    private SuggestionManager suggestionManager;

    public CommitteeCampSuggestionsOptions(UserManager userManager, CampManager campManager, SuggestionManager suggestionManager) {
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

        // Fetch suggestions from camp, accepted, rejected or pending
        if (suggestionManager.getSelectedSuggestionStatus() == SuggestionStatus.PENDING) {

            this.noCampTitle = "No pending suggestion for this camp at the moment. Please raise suggestion under 'Raise Suggestion'.";
            super.setOptions(suggestionManager.getPendingStudentCampSuggestionTitles(userManager.getCurrentUser().getName(), campManager.getSelectedCampID()));
            this.suggestionIDs = suggestionManager.getPendingStudentCampSuggestionIDs(userManager.getCurrentUser().getName(), campManager.getSelectedCampID());

        } else if (suggestionManager.getSelectedSuggestionStatus() == SuggestionStatus.ACCEPTED) {

            this.noCampTitle = "No approved suggestion for this camp at the moment. Please raise suggestion under 'Raise Suggestion'.";
            super.setOptions(suggestionManager.getAcceptedStudentCampSuggestionTitles(userManager.getCurrentUser().getName(), campManager.getSelectedCampID()));
            this.suggestionIDs = suggestionManager.getAcceptedStudentCampSuggestionIDs(userManager.getCurrentUser().getName(), campManager.getSelectedCampID());

        } else if (suggestionManager.getSelectedSuggestionStatus() == SuggestionStatus.REJECTED) {

            this.noCampTitle = "No rejected suggestion for this camp at the moment. Please raise suggestion under 'Raise Suggestion'.";
            super.setOptions(suggestionManager.getRejectedStudentCampSuggestionTitles(userManager.getCurrentUser().getName(), campManager.getSelectedCampID()));
            this.suggestionIDs = suggestionManager.getRejectedStudentCampSuggestionIDs(userManager.getCurrentUser().getName(), campManager.getSelectedCampID());

        }
    }
}
