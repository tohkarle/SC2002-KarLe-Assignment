package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.CampStudentsOptions;
import cams.utils.Dismiss;

public class CampStudentsView implements View {

    private Navigation navigation;
    private int selectedCampID;

    /**
     * Initialize the AllCampsView
     * @param navigation Navigation object used to control navigation of the application
     */
    public CampStudentsView(Navigation navigation, int selectedCampID) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

    /**
     * Render the CampStudentsView
     */
    public void render() {

        // Display committee members and attendee
        Options campStudentsOptions = new CampStudentsOptions(selectedCampID);
        campStudentsOptions.display("Committee members and attendees of this camp:");

        // Allow user to navigate back
        if (campStudentsOptions.selection() == Dismiss.intOption()) {
            navigation.dismissView();
        }
    }
}
