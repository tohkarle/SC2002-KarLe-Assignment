package cams.ui.camp;

import cams.components.input.ChooseBetweenTwoOptions;
import cams.interfaces.IntInput;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class CampVisibilityUI implements UI {

    private Camp camp;
    private String title;

    public CampVisibilityUI(Camp camp, String title) {
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        IntInput choose = new ChooseBetweenTwoOptions("On", "Off");
        int option = choose.getValidInt(title);
        Boolean visibility = (option == 1);
        if (option == Dismiss.intOption()) { return; }
        camp.setVisibility(visibility);
    }    
}
