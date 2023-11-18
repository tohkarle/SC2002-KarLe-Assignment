package cams.view.camp;

import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.option.camp.UserCampOptions;
import cams.utils.Dismiss;

public class RegisteredCampsView extends View {

    private CampManager campManager;

    // Options for this view:
    private UserCampOptions userCampOptions;

    public RegisteredCampsView(Navigation navigation, CampManager campManager) {
        super(navigation);
        this.campManager  = campManager;
    }

    public void render() {
        // Display camps
        userCampOptions = (UserCampOptions) super.getOptions("camp.UserCampOptions");

        // Fetch latest registered camps
        userCampOptions.fetchUserCamps();

        // Display registered camps
        userCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int option = userCampOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        campManager.setSelectedCampID(option);

        // Navigate to CampInfoView
        super.getNavigation().navigateTo("camp.RegisteredCampInfoView");
    }
}
