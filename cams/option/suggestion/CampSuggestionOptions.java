package cams.option.suggestion;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.model.SuggestionStatus;
import cams.utils.Dismiss;
import cams.utils.Page;

public class CampSuggestionOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> suggestionIDs;
    private CampManager campManager;
    private SuggestionManager suggestionManager;

    public CampSuggestionOptions(CampManager campManager, SuggestionManager suggestionManager) {
        this.campManager = campManager;
        this.suggestionManager = suggestionManager;
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

        // Fetch suggestions from camp, accepted, rejected or pending
        if (suggestionManager.getSelectedSuggestionStatus() == SuggestionStatus.PENDING) {

            this.noCampTitle = "No pending suggestion for this camp at the moment. Please come back at a later time.";
            super.setOptions(suggestionManager.getPendingCampSuggestionTitles(campManager.getSelectedCampID()));
            this.suggestionIDs = suggestionManager.getPendingCampSuggestionIDs(campManager.getSelectedCampID());

        } else if (suggestionManager.getSelectedSuggestionStatus() == SuggestionStatus.ACCEPTED) {

            this.noCampTitle = "No approved suggestion for this camp at the moment. Please come back at a later time.";
            super.setOptions(suggestionManager.getAcceptedCampSuggestionTitles(campManager.getSelectedCampID()));
            this.suggestionIDs = suggestionManager.getAcceptedCampSuggestionIDs(campManager.getSelectedCampID());

        } else if (suggestionManager.getSelectedSuggestionStatus() == SuggestionStatus.REJECTED) {

            this.noCampTitle = "No rejected suggestion for this camp at the moment. Please come back at a later time.";
            super.setOptions(suggestionManager.getRejectedCampSuggestionTitles(campManager.getSelectedCampID()));
            this.suggestionIDs = suggestionManager.getRejectedCampSuggestionIDs(campManager.getSelectedCampID());

        }
    }
}
