package cams.option.camp;

import java.util.Arrays;

import cams.components.input.GetSelection;
import cams.interfaces.IntInput;
import cams.model.Camp;

public class CreatedCampInfoOptions extends CampInfoOptions {

    public CreatedCampInfoOptions(Camp camp) {
        super(camp);
        this.changeOption();
    }

    public void changeOption() {
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "(1) Edit camp details",
            "(2) Manage enquiries",
            "(3) Manage suggestions",
            "(4) Create report",
            "(5) Delete Camp"
        ));
    }

    @Override
    public int selection() {
        IntInput selection = new GetSelection(-1, 5);
        return selection.getValidInt("Your selection: ");
    }
}
