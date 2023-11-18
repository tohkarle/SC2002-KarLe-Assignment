package cams.ui.camp;

import cams.components.input.GetStringInput;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class EditCampNameUI extends GetStringInput implements UI {

    private Camp camp;
    private String title;
    
    public EditCampNameUI(Camp camp, String title) {
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        String name = super.getValidString(title);
        if (name.equals(Dismiss.stringOption())) { return; }
        camp.setCampName(name);
    }
}