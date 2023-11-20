package cams.option.suggestion;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.SuggestionManager;
import cams.model.SuggestionStatus;
import cams.utils.Dismiss;
import cams.utils.Page;

public class CampSuggestionOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> suggestionIDs;
    private SuggestionStatus suggestionStatus;
    private int selectedCampID;

    public CampSuggestionOptions(SuggestionStatus suggestionStatus, int selectedCampID) {
        this.suggestionStatus = suggestionStatus;
        this.selectedCampID = selectedCampID;
        this.fetchCampSuggestions();
    }

    @Override
    public void display(String title) {
        fetchCampSuggestions();
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

    private void fetchCampSuggestions() {

        SuggestionManager suggestionManager = SuggestionManager.getInstance();

        // Fetch suggestions from camp, accepted, rejected or pending
        if (suggestionStatus == SuggestionStatus.PENDING) {

            this.noCampTitle = "No pending suggestion for this camp at the moment. Please come back at a later time.";
            super.setOptions(suggestionManager.getPendingCampSuggestionTitles(selectedCampID));
            this.suggestionIDs = suggestionManager.getPendingCampSuggestionIDs(selectedCampID);

        } else if (suggestionStatus == SuggestionStatus.ACCEPTED) {

            this.noCampTitle = "No approved suggestion for this camp at the moment. Please come back at a later time.";
            super.setOptions(suggestionManager.getAcceptedCampSuggestionTitles(selectedCampID));
            this.suggestionIDs = suggestionManager.getAcceptedCampSuggestionIDs(selectedCampID);

        } else if (suggestionStatus == SuggestionStatus.REJECTED) {

            this.noCampTitle = "No rejected suggestion for this camp at the moment. Please come back at a later time.";
            super.setOptions(suggestionManager.getRejectedCampSuggestionTitles(selectedCampID));
            this.suggestionIDs = suggestionManager.getRejectedCampSuggestionIDs(selectedCampID);

        }
    }
}
