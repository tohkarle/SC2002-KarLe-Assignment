package cams.view.camp;

import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.CampInfoOptions;
import cams.utils.Dismiss;

public class CampInfoView extends View {

    // Options for this view:
    private CampInfoOptions campInfoOptions;

    public CampInfoView(Navigation navigation) {
        super(navigation);
    }

    public void render() {

        campInfoOptions = (CampInfoOptions) super.getOptions("camp.CampInfoOptions");

        // Update camp details to latest
        campInfoOptions.initializeTempCamp();
        campInfoOptions.updateCampInfo();

        campInfoOptions.display("Camp details: ");

        // Allow user to go back
        if (campInfoOptions.selection() == Dismiss.intOption()) {
            super.getNavigation().dismissView();
            return;
        }
    }
}
