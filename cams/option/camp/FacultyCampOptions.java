package cams.option.camp;

import java.util.ArrayList;

import cams.components.option.SelectableOptions;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.Page;

public class FacultyCampOptions extends SelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> campIDs;

    public FacultyCampOptions(UserManager userManager, CampManager campManager) {
        // Fetch all camps from faculty
        this.noCampTitle = "No camp has been created under this faculty. Please conme back at a later time.";
        super.setOptions(campManager.getAllFacultyCampNames(userManager.getCurrentUser().getFaculty()));
        this.campIDs = campManager.getAllFacultyCampIDs(userManager.getCurrentUser().getFaculty());
    }

    @Override
    public void display(String title) {
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
}
