package cams.option.suggestion;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
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
        this.fetchFacultyCamps();
    }

    @Override
    public void display(String title) {
        fetchFacultyCamps();
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

    private void fetchFacultyCamps() {
        // Fetch all suggestions from camp
        this.noCampTitle = "No suggestion has been created under this camp. Please conme back at a later time.";
        super.setOptions(suggestionManager.getAllCampSuggestionContents(campManager.getSelectedID()));
        this.suggestionIDs = suggestionManager.getAllCampSuggestionIDs(campManager.getSelectedID());
    }
}
