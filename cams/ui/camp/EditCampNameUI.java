package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class EditCampNameUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;
    
    public EditCampNameUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        String name = getInput.getValidString(title);
        if (name.equals(Dismiss.stringOption())) { return; }
        camp.setCampName(name);
    }
}