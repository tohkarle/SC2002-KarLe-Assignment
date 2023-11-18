package cams.view.camp;

import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.CampInfoOptions;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Dismiss;

public class RegisteredCampInfoView extends View {
    
    // Options for this view:
    private CampInfoOptions campInfoOptions;

    // UIs for this view:
    private UI withdrawFromCampUI;

    public RegisteredCampInfoView(Navigation navigation) {
        super(navigation);
    }

    public void render() {

        campInfoOptions = (CampInfoOptions) super.getOptions("camp.CampInfoOptions");

        // Update registered camp details to latest
        campInfoOptions.updateRegisteredCampInfo();

        // Display registered camp details
        campInfoOptions.viewOnly("Camp details: ");

        // Allow student to go back or withdraw from camp
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(-1, 1);
        if (selectionWithDismiss.getValidInt("Your selection: ") == Dismiss.intOption() ) { 
            super.getNavigation().dismissView();
            return; 
        }

        withdrawFromCampUI = super.getUI("camp.WithdrawFromCampUI");
        withdrawFromCampUI.body();
    }
}
