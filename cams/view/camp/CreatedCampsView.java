package cams.view.camp;

import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.option.camp.UserCampOptions;
import cams.utils.Dismiss;

public class CreatedCampsView extends View {

    private CampManager campManager;

    // Options for this view:
    private UserCampOptions userCampOptions;

    public CreatedCampsView(Navigation navigation, CampManager campManager) {
        super(navigation);
        this.campManager  = campManager;
    }

    public void render() {

        userCampOptions = (UserCampOptions) super.getOptions("camp.UserCampOptions");

        // Fetch latest created camps
        userCampOptions.fetchUserCamps();

        // Label camp as new if staff just created camp
        if (justCreatedCamp()) { labelCampAsNew(); }

        // Display camps
        userCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int option = userCampOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().popToRoot();
            return; 
        }
        campManager.setSelectedCampID(option);

        // Navigate to EditCampView
        super.getNavigation().navigateTo("camp.CreatedCampInfoView");
    }

    public void labelCampAsNew() {
        String name = userCampOptions.getOption(userCampOptions.getOptionsSize() - 1);
        System.out.println("Labeling: " + name);
        userCampOptions.replaceOption(name, name + " (New)");
    }

    public boolean justCreatedCamp() {
        return (super.getNavigation().getPreviousView().equals("camp.CreateCampView"));
    }
}
