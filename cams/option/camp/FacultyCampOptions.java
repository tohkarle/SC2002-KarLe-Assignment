package cams.option.camp;

import java.util.ArrayList;

import cams.components.option.DismissableSelectableOptions;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.Page;

public class FacultyCampOptions extends DismissableSelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> campIDs;
    private UserManager userManager;
    private CampManager campManager;

    public FacultyCampOptions(UserManager userManager, CampManager campManager) {
        this.userManager = userManager;
        this.campManager = campManager;
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
        return this.campIDs.get(option - 1);
    }

    private void fetchFacultyCamps() {
        // Fetch all camps from faculty
        this.noCampTitle = "No camp has been created under this faculty. Please conme back at a later time.";
        super.setOptions(campManager.getAllFacultyCampNames(userManager.getCurrentUser().getFaculty()));
        this.campIDs = campManager.getAllFacultyCampIDs(userManager.getCurrentUser().getFaculty());
    }
}
