package cams.ui.camp;

import cams.components.LoadingIndicator;
import cams.components.option.Options;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.model.Camp;

public class EditCampUI implements UI {

    private Options editCampOptions;
    private Navigation navigation;
    private CampManager campManager;
    private IntInput confirm;

    // Edit camp UIs
    private UI editCampNameUI;
    private UI editCampFacultyUI;
    private UI editCampLocationUI;
    private UI editCampDescriptionUI;
    private UI editCampVisibility;
    private UI editCampRegiatrationClosingDateUI;
    private UI editCampDatesUI;
    private UI editCampTotalSlotsUI;
    private UI editCampCommitteeSlotsUI;

    public EditCampUI(Navigation navigation, CampManager campManager, Options editCampOptions, IntInput confirm) {
        this.navigation = navigation;
        this.campManager = campManager;
        this.editCampOptions = editCampOptions;
        this.confirm = confirm;
    }

    @Override
    public void body() {

        Camp tempCamp = campManager.getTempCamp();

        // Create and initialize all UIs for edit camp
        editCampNameUI = new EditCampNameUI(tempCamp, editCampOptions.getOption(0));
        editCampFacultyUI = new EditCampFacultyUI(tempCamp, editCampOptions.getOption(1));
        editCampLocationUI = new EditCampLocationUI(tempCamp, editCampOptions.getOption(2));
        editCampDescriptionUI = new EditCampDescriptionUI(tempCamp, editCampOptions.getOption(3));
        editCampVisibility = new EditCampVisibilityUI(tempCamp, editCampOptions.getOption(4));
        editCampDatesUI = new EditCampDatesUI(tempCamp, editCampOptions.getOption(5), editCampOptions.getOption(6));
        editCampRegiatrationClosingDateUI = new EditCampRegiatrationClosingDateUI(tempCamp, editCampOptions.getOption(7));
        editCampTotalSlotsUI = new EditCampTotalSlotsUI(tempCamp, campManager, editCampOptions.getOption(8));
        editCampCommitteeSlotsUI = new EditCampCommitteeSlotsUI(tempCamp, campManager, editCampOptions.getOption(9));

        UI[] editCampUIs = new UI[] {
            editCampNameUI,
            editCampFacultyUI,
            editCampLocationUI,
            editCampDescriptionUI,
            editCampVisibility,
            editCampDatesUI,
            editCampRegiatrationClosingDateUI,
            editCampTotalSlotsUI,
            editCampCommitteeSlotsUI
        };

        // Display corresponding UI
        if (campManager.getSelectedCampInfo() <= editCampUIs.length) {
            campManager.setIsEditingCamp(true);
            editCampUIs[campManager.getSelectedCampInfo() - 1].body();
        } else if (campManager.getSelectedCampInfo() == editCampUIs.length + 1) {
            // Confirm changes or discard
            if (confirm.getValidInt("Confirm changes?") != 1) { return; }
            LoadingIndicator.editingLoadingIndicator("camp");
            campManager.setIsEditingCamp(false);
            campManager.updateCamp(campManager.getTempCamp());
            navigation.dismissView();
        }
    }
}
