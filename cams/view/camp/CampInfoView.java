package cams.view.camp;

import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.CampInfoOptions;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Dismiss;

public class CampInfoView extends View {

    // Options for this view:
    private CampInfoOptions campInfoOptions;

    // UIs for this view:
    private UI withdrawFromCampUI;

    public CampInfoView(Navigation navigation) {
        super(navigation);
    }

    public void render() {

        campInfoOptions = (CampInfoOptions) super.getOptions("camp.CampInfoOptions");

        // Update camp details to latest
        campInfoOptions.updateCampInfo();

        // If student is viewing registered camps, they will have an option to withdraw from the camp
        if (viewingRegisteredCampInfo()) { 
            campInfoOptions.attendeeCampInfoOptions();
        }

        // If staff is viewing created camps, they will have an option to edit camp, manage enquiries, suggestionse etc
        if (viewingCreatedCampInfo()) {
            campInfoOptions.staffCampInfoOptions();
        }

        campInfoOptions.viewOnly("Camp details: ");

        if (viewingRegisteredCampInfo()) {
            // Allow student to go back or withdraw from camp
            IntInput selectionWithDismiss = new GetSelectionWithDismissUI(-1, 1);
            if (selectionWithDismiss.getValidInt("Your selection: ") == Dismiss.intOption() ) { 
                super.getNavigation().dismissView();
                return; 
            }

            withdrawFromCampUI = super.getUI("camp.WithdrawFromCampUI");
            withdrawFromCampUI.body();
        }

        if (viewingCreatedCampInfo()) {
            // Allow student to go back or withdraw from camp
            IntInput selectionWithDismiss = new GetSelectionWithDismissUI(-1, 4);
            if (selectionWithDismiss.getValidInt("Your selection: ") == Dismiss.intOption() ) { 
                super.getNavigation().dismissView();
                return; 
            }
        }

        // Allow user to go back only, since there is no withdraw option
        if (campInfoOptions.dismiss() == Dismiss.intOption()) {
            super.getNavigation().dismissView();
            return;
        }
    }

    public boolean viewingRegisteredCampInfo () {
        return (super.getNavigation().getPreviousView().equals("camp.RegisteredCampsView"));
    }

    public boolean viewingCreatedCampInfo() {
        return (super.getNavigation().getPreviousView().equals("camp.CreatedCampsView"));
    }
}
