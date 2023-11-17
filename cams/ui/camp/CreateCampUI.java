package cams.ui.camp;

import cams.components.option.CreateCampOptions;
import cams.components.option.Options;
import cams.interfaces.InputField;
import cams.interfaces.IntInput;
import cams.ui.ConfirmOrDiscardUI;
import cams.utils.CampUtil;

public class CreateCampUI implements InputField {

    private int staffID;
    private CampUtil campUtil;
    private Options createCampOptions;

    // Edit camp UIs
    private InputField createCampNameUI;
    private InputField createCampFacultyUI;
    private InputField createCampVisibilityUI;
    private InputField createCampDatesUI;

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

    public boolean focused() {

            // Enter name, faculty, visibility and dates
            if (!createCampNameUI.focused()) return false;
            if (!createCampFacultyUI.focused()) return false;
            if (!createCampVisibilityUI.focused()) return false;
            if (!createCampDatesUI.focused()) return false;

            // Confirm delete or discard and go back
            IntInput confirmOrDiscard = new ConfirmOrDiscardUI("create");
            if (confirmOrDiscard.getValidInt() != 1) { return false; }

            // Create camp
            campUtil.createCamp(staffID);
            return true;
    }
}