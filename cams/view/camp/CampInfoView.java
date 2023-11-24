package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.model.Camp;
import cams.option.camp.CampInfoOptions;
import cams.utils.Dismiss;

/**
 * View object for Camp Info page
 */
public class CampInfoView implements View {

    private Navigation navigation;
    private int selectedCampID;

    /**
     * Initialize the CampInfoView
     * @param navigation Navigation object used to control navigation of the application
     * @param selectedCampID int of the selected camp ID
     */
    public CampInfoView(Navigation navigation, int selectedCampID) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

    /**
     * Render the CampInfoView
     */
    public void render() {

        CampManager campManager = CampManager.getInstance();
        Camp camp = campManager.getCamp(selectedCampID);
        Options campInfoOptions = new CampInfoOptions(camp);

        // Display camp details
        campInfoOptions.display("Camp details: ");

        // Allow user to go back
        if (campInfoOptions.selection() == Dismiss.intOption()) {
            navigation.dismissView();
            return;
        }
    }
}
