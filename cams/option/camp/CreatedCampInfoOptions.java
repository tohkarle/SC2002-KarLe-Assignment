package cams.option.camp;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Camp;

/**
 * Options object for camp management selection in the CreatedCampInfoView
 */
public class CreatedCampInfoOptions extends CampInfoOptions {

    /**
     * Initialize the camp information options
     * @param camp Camp object to display information of
     */
    public CreatedCampInfoOptions(Camp camp) {
        super(camp);
        this.changeOption();
    }

    /**
     * Change the options to include options for camp management by staff
     */
    public void changeOption() {
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "(1) Edit camp details",
            "(2) Manage enquiries",
            "(3) Manage suggestions",
            "(4) Generate registration report",
            "(5) Generate performance report",
            "(6) Delete Camp"
        ));
    }

    @Override
    public int selection() {
        IntInput selection = new GetSelectionWithDismiss(1, 6);
        return selection.getValidInt("Your selection: ");
    }
}
