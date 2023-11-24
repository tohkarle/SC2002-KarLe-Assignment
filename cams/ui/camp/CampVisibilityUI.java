package cams.ui.camp;

import cams.components.input.ChooseBetweenTwoOptions;
import cams.interfaces.IntInput;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

/**
 * UI object for editing the visibility of a camp
 */
public class CampVisibilityUI implements UI {

    private Camp camp;
    private String title;

    /**
     * Initialize the CampVisibilityUI
     * @param camp Camp object to be edited
     * @param title Title of the UI
     */
    public CampVisibilityUI(Camp camp, String title) {
        this.camp = camp;
        this.title = title;
    }

    /**
     * Executes user interaction logic for editing the visibility of a camp
     */
    @Override
    public void body() {
        IntInput choose = new ChooseBetweenTwoOptions("On", "Off");
        int option = choose.getValidInt(title);
        Boolean visibility = (option == 1);
        if (option == Dismiss.intOption()) { return; }
        camp.setVisibility(visibility);
    }    
}
