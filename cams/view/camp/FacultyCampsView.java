package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.utils.Dismiss;

public class FacultyCampsView extends View {

    private CampManager campManager;

    // Options for this view:
    private Options facultyCampOptions;

    public FacultyCampsView(Navigation navigation, CampManager campManager) {
        super(navigation);
        this.campManager  = campManager;
    }

    public void render() {
        // Display camps
        facultyCampOptions = super.getOptions("camp.FacultyCampOptions");
        facultyCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int option = facultyCampOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        campManager.setViewCampDetail(option);

        // Navigate to CampInfoView
        super.getNavigation().navigateTo("camp.CampInfoView");
    }
}