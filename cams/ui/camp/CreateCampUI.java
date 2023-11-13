package cams.ui.camp;

import cams.components.option.CreateCampOptions;
import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.IntInput;
import cams.ui.ConfirmOrDiscardUI;
import cams.utils.CampUtil;

public class CreateCampUI implements Input {

    private int staffID;
    private CampUtil campUtil;
    private Options createCampOptions;

    // Edit camp UIs
    private Input createCampNameUI;
    private Input createCampFacultyUI;
    private Input createCampVisibilityUI;
    private Input createCampDatesUI;

    public CreateCampUI(int staffID) {
        this.staffID = staffID;
        this.campUtil = new CampUtil();
        this.createCampOptions = new CreateCampOptions();

        // Initialize all UIs
        this.createCampNameUI = new CampNameUI(campUtil, createCampOptions.getOption(0));
        this.createCampFacultyUI = new CampFacultyUI(campUtil, createCampOptions.getOption(1));
        this.createCampVisibilityUI = new CampVisibilityUI(campUtil, createCampOptions.getOption(2));
        this.createCampDatesUI = new CampDatesUI(campUtil, createCampOptions.getOption(3), createCampOptions.getOption(4));
    }

    public boolean getInput() {

            // Enter name, faculty, visibility and dates
            if (!createCampNameUI.getInput()) return false;
            if (!createCampFacultyUI.getInput()) return false;
            if (!createCampVisibilityUI.getInput()) return false;
            if (!createCampDatesUI.getInput()) return false;

            // Confirm delete or discard and go back
            IntInput confirmOrDiscard = new ConfirmOrDiscardUI("create");
            if (confirmOrDiscard.getValidInt() != 1) { return false; }

            // Create camp
            campUtil.createCamp(staffID);
            return true;
    }
}
