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

    public FacultyCampOptions() {
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
        
        UserManager userManager = UserManager.getInstance();
        CampManager campManager = CampManager.getInstance();

        // Fetch all camps from faculty
        this.noCampTitle = "No camp has been created under this faculty. Please conme back at a later time.";
        super.setOptions(campManager.getAllFacultyCampNames(userManager.getCurrentUser().getFaculty()));
        this.campIDs = campManager.getAllFacultyCampIDs(userManager.getCurrentUser().getFaculty());
    }
}
