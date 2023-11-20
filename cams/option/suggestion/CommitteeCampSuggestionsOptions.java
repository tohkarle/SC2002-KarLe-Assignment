package cams.option.suggestion;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.model.SuggestionStatus;
import cams.utils.Dismiss;
import cams.utils.Page;

public class CommitteeCampSuggestionsOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> suggestionIDs;
    private SuggestionStatus suggestionStatus;
    private int selectedCampID;

    public CommitteeCampSuggestionsOptions(SuggestionStatus suggestionStatus, int selectedCampID) {
        this.suggestionStatus = suggestionStatus;
        this.fetchStudentCampSuggestions();
        this.selectedCampID = selectedCampID;
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

        UserManager userManager = UserManager.getInstance();
        SuggestionManager suggestionManager = SuggestionManager.getInstance();

        // Fetch suggestions from camp, accepted, rejected or pending
        if (suggestionStatus == SuggestionStatus.PENDING) {

            this.noCampTitle = "No pending suggestion for this camp at the moment. Please raise suggestion under 'Raise Suggestion'.";
            super.setOptions(suggestionManager.getPendingStudentCampSuggestionTitles(userManager.getCurrentUser().getName(), selectedCampID));
            this.suggestionIDs = suggestionManager.getPendingStudentCampSuggestionIDs(userManager.getCurrentUser().getName(), selectedCampID);

        } else if (suggestionStatus == SuggestionStatus.ACCEPTED) {

            this.noCampTitle = "No approved suggestion for this camp at the moment. Please raise suggestion under 'Raise Suggestion'.";
            super.setOptions(suggestionManager.getAcceptedStudentCampSuggestionTitles(userManager.getCurrentUser().getName(), selectedCampID));
            this.suggestionIDs = suggestionManager.getAcceptedStudentCampSuggestionIDs(userManager.getCurrentUser().getName(), selectedCampID);

        } else if (suggestionStatus == SuggestionStatus.REJECTED) {

            this.noCampTitle = "No rejected suggestion for this camp at the moment. Please raise suggestion under 'Raise Suggestion'.";
            super.setOptions(suggestionManager.getRejectedStudentCampSuggestionTitles(userManager.getCurrentUser().getName(), selectedCampID));
            this.suggestionIDs = suggestionManager.getRejectedStudentCampSuggestionIDs(userManager.getCurrentUser().getName(), selectedCampID);

        }
    }
}
