package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.utils.Dismiss;

public class CreatedCampsView extends View {

      private CampManager campManager;

    // Options for this view:
    private Options userCampOptions;

    public CreatedCampsView(Navigation navigation, CampManager campManager) {
        super(navigation);
        this.campManager  = campManager;
    }

    public void render() {
        // Display camps
        userCampOptions = super.getOptions("camp.UserCampOptions");
        userCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int option = userCampOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        campManager.setViewCampDetail(option);

        // Navigate to EditCampView
        super.getNavigation().navigateTo("camp.EditCampView");
    }

    // public void labelCampNew() {
    //     if (justCreated) {
    //         String name = campOptions.getOption(campOptions.getOptionsSize() - 1);
    //         campOptions.replaceOption(name, name + " (New)");
    //     }
    // }
}
