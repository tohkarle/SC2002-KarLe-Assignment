package cams.option.camp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Camp;

/**
 * This class extends CampInfoOptions and provides additional options for camps created by staff.
 */
public class CreatedCampInfoOptions extends CampInfoOptions {

    private List<String> additionalOptions;

    /**
     * Initialize the camp information options
     * @param camp Camp object to display information of
     */
    public CreatedCampInfoOptions(Camp camp) {
        super(camp);
        this.additionalOptions = new ArrayList<>();
        this.changeOption();
    }

    /**
     * Change the options to include options for camp management by staff
     */
    public void changeOption() {
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        
        this.additionalOptions = Arrays.asList(
            "Edit camp details",
            "View registered students",
            "Manage enquiries",
            "Manage suggestions",
            "Generate registration report",
            "Generate performance report",
            "Delete Camp"
        );

        for (int i = 0; i < this.additionalOptions.size(); i++) {
            super.getOptions().add(String.format("(%d) %s", i + 1, this.additionalOptions.get(i)));
        }
    }

    /**
     * Get the user's selection from the list of options.
     * @return The user's selection as an integer.
     */
    @Override
    public int selection() {
        IntInput selection = new GetSelectionWithDismiss(1, this.additionalOptions.size());
        return selection.getValidInt("Your selection: ");
    }
}
