package cams.option.camp;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.CampManager;
import cams.utils.Dismiss;
import cams.utils.Page;

public class AllCampOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> campIDs;
    private CampManager campManager;

    public AllCampOptions(CampManager campManager) {
        this.campManager = campManager;
        this.fetchAllCamps();
    }

    @Override
    public void display(String title) {
        fetchAllCamps();
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
        return this.campIDs.get(option - 1);
    }

    private void fetchAllCamps() {
        // Fetch all camps
        this.noCampTitle = "No camp has been created.";
        super.setOptions(campManager.getAllCampNames());
        this.campIDs = campManager.getAllCampIDs();
    }
}
