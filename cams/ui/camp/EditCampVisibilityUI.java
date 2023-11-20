package cams.ui.camp;

import cams.components.input.ChooseBetweenTwoOptions;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class EditCampVisibilityUI extends ChooseBetweenTwoOptions implements UI {

    private Camp camp;
    private String title;

    public EditCampVisibilityUI(Camp camp, String title) {
        super("On", "Off");
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        int option = super.getValidInt(title);
        Boolean visibility = (option == 1);
        if (option == Dismiss.intOption()) { return; }
        camp.setVisibility(visibility);
    }    
}
