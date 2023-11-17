package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.utils.Dismiss;

public class AllCampsView extends View {

    private CampManager campManager;

    // Options for this view:
    private Options allCampOptions;

    public AllCampsView(Navigation navigation, CampManager campManager) {
        super(navigation);
        this.campManager  = campManager;
    }

    public void render() {
        // Display camps
        allCampOptions = super.getOptions("camp.AllCampOptions");
        allCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int option = allCampOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        campManager.setViewCampDetail(option);

        // Navigate to CampInfoView
        super.getNavigation().navigateTo("camp.CampInfoView");
    }
}
