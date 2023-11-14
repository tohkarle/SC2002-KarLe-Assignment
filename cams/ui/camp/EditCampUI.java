package cams.ui.camp;

import cams.components.option.EditCampOptions;
import cams.components.option.Options;
import cams.interfaces.InputField;
import cams.interfaces.UI;
import cams.utils.CampUtil;

public class EditCampUI implements UI {

    private int option;
    private InputField[] editCampUIs;
    private Options editCampOptions;

    // Edit camp UIs
    private InputField editCampNameUI;
    private InputField editCampFacultyUI;
    private InputField editCampLocationUI;
    private InputField editCampDescriptionUI;
    private InputField editCampVisibility;
    private InputField editCampRegiatrationClosingDateUI;
    private InputField editCampDatesUI;
    private InputField editCampTotalSlotsUI;
    private InputField editCampCommitteeSlotsUI;

    public EditCampUI(int option, CampUtil campUtil) {

        this.option = option;
        this.editCampOptions = new EditCampOptions();

        // Create and initialize all UIs for edit camp
        this.editCampNameUI = new CampNameUI(campUtil, editCampOptions.getOption(0));
        this.editCampFacultyUI = new CampFacultyUI(campUtil, editCampOptions.getOption(1));
        this.editCampLocationUI = new CampLocationUI(campUtil, editCampOptions.getOption(2));
        this.editCampDescriptionUI = new CampDescriptionUI(campUtil, editCampOptions.getOption(3));
        this.editCampVisibility = new CampVisibilityUI(campUtil, editCampOptions.getOption(4));
        this.editCampRegiatrationClosingDateUI = new CampRegiatrationClosingDateUI(campUtil, editCampOptions.getOption(5));
        this.editCampDatesUI = new CampDatesUI(campUtil, editCampOptions.getOption(6), editCampOptions.getOption(7));
        this.editCampTotalSlotsUI = new CampTotalSlotsUI(campUtil, editCampOptions.getOption(8));
        this.editCampCommitteeSlotsUI = new CampCommitteeSlotsUI(campUtil, editCampOptions.getOption(9));

        this.editCampUIs = new InputField[] {
            this.editCampNameUI,
            this.editCampFacultyUI,
            this.editCampLocationUI,
            this.editCampDescriptionUI,
            this.editCampVisibility,
            this.editCampRegiatrationClosingDateUI,
            this.editCampDatesUI,
            this.editCampTotalSlotsUI,
            this.editCampCommitteeSlotsUI
        };
    }

    public void body() {
        // Display corresponding UI
        if (option <= editCampUIs.length) {
            editCampUIs[option - 1].focused();
        }
    }
}
