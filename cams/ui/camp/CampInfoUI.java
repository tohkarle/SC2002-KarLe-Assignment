package cams.ui.camp;

import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.option.camp.CampInfoOptions;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Dismiss;

public class CampInfoUI implements UI {

    private Navigation navigation;
    private CampInfoOptions campInfoOptions;

    public CampInfoUI(Navigation navigation, UserManager userManager, CampManager campManager, CampInfoOptions campInfoOptions) {
        this.navigation = navigation;
        this.campInfoOptions = campInfoOptions;
    }

    public void body() {
        // If student is viewing registered camps, they will have an option to withdraw from the camp
        if (viewingRegisteredCampInfo()) { 
            campInfoOptions.setCampInfo(); 
            return;
        }

        if (!viewingRegisteredCampInfo()) {
            // Allow user to go back only, since there is no withdraw option
            campInfoOptions.dismiss();
            return;
        }

        // Allow student to go back or withdraw from camp
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(-1, 1);
        if (selectionWithDismiss.getValidInt("Your selection: ") == Dismiss.intOption() ) { 
            navigation.dismissView();
            return; 
        }
    }

    public boolean viewingRegisteredCampInfo () {
        return (navigation.getPreviousView().equals("camp.RegisteredCampsView"));
    }
}
