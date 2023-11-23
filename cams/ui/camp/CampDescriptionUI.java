package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class CampDescriptionUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;

    public CampDescriptionUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        String description = getInput.getValidString(title);
        if (description.equals(Dismiss.stringOption())) { return; }
        camp.setDescription(description);
    }
}