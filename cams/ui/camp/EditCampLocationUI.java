package cams.ui.camp;

import cams.components.input.GetStringInput;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class EditCampLocationUI extends GetStringInput implements UI {

    private Camp camp;
    private String title;
    
    public EditCampLocationUI(Camp camp, String title) {
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        String location = super.getValidString(title);
        if (location.equals(Dismiss.stringOption())) { return; }
        camp.setLocation(location);
    }
}