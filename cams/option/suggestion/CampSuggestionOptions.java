package cams.option.suggestion;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.SuggestionManager;
import cams.model.SuggestionStatus;
import cams.utils.Dismiss;
import cams.utils.Page;

/**
 * Options object for selecting a camp suggestion to view in the CampSuggestionView
 */
public class CampSuggestionOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> suggestionIDs;
    private SuggestionStatus suggestionStatus;
    private int selectedCampID;

    /**
     * Initialize the CampSuggestion options
     * @param suggestionStatus The status of the suggestion to view
     * @param selectedCampID The camp ID of the camp to view suggestions for
     */
    public CampSuggestionOptions(SuggestionStatus suggestionStatus, int selectedCampID) {
        this.suggestionStatus = suggestionStatus;
        this.selectedCampID = selectedCampID;
        this.fetchCampSuggestions();
    }


    /**
     * The method to display the options
     * @param title The title of what the options are about
     */
    @Override
    public void display(String title) {
        fetchCampSuggestions();
        if (super.getOptionsSize() == 0) {
            Page.header(this.noCampTitle);
        } else {
            super.display(title);
        }
    }


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    @Override
    public int selection() {
        int option = super.selection();
        if (option == Dismiss.intOption()) { return option; }
        return this.suggestionIDs.get(option - 1);
    }


    /**
     * A method to build the options list
     */
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
