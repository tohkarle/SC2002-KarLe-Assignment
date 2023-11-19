package cams.view.camp;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.option.camp.CampInfoOptions;
import cams.utils.Dismiss;

public class EditCampView extends View {

    private CampManager campManager;

    // Options for this view:
    private CampInfoOptions editCampOptions;

    // UIs for this view:
    private UI editCampUI;

    public EditCampView(Navigation navigation, CampManager campManager) {
        super(navigation);
        this.campManager = campManager;
    }

    public void render() {

        editCampOptions = (CampInfoOptions) super.getOptions("camp.EditCampOptions");

        // Remove Staff-in-charge, add Update changes, Manage enquiries, Create report and Delete camp options
        editCampOptions.updateCampInfo();

        // Display camp info for editing
        editCampOptions.display("Select the field you want to edit: ");

        // Let user choose the field to edit
        int option = editCampOptions.selection();
        if (option == Dismiss.intOption()) { 
            campManager.clearTempCamp();
            super.getNavigation().dismissView();
            return; 
        }
        campManager.setSelectedCampInfo(option);

        editCampUI = super.getUI("camp.EditCampUI");
        editCampUI.body();
    }
}
