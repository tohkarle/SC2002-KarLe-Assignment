package cams.option.camp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.model.Camp;

/**
 * This class extends CampInfoOptions and provides additional options for camps that the user is registered for.
 */
public class RegisteredCampInfoOptions extends CampInfoOptions {

    private List<String> additionalOptions;
    private UserManager userManager;
    private CampManager campManager;

    /**
     * Initialize the camp information options
     * @param camp The selected Camp object
     */
    public RegisteredCampInfoOptions(Camp camp) {
        super(camp);
        this.additionalOptions = new ArrayList<>();
        this.userManager = UserManager.getInstance();
        this.campManager = CampManager.getInstance();
        this.changeOption();
    }

    /**
     * Change the options based on whether the current user is a committee member or attendee of the camp.
     * If the user is a committee member, the options include managing enquiries, giving suggestions, managing their own suggestions, and generating a registration or enquiry report.
     * If the user is an attendee, the options include raising enquiries, managing their own enquiries, and withdrawing from the camp.
     */
    public void changeOption() {

        if (campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), super.getCamp().getId())) {
            this.additionalOptions = Arrays.asList(
                "Manage enquiries",
                "Give suggestions",
                "Manage your suggestions",
                "Generate registration report",
                "Generate enquiry report"
            );
        } else {
            this.additionalOptions = Arrays.asList(
                "Raise enquiry",
                "Manage your enquiries",
                "Withdraw from camp"
            );
        }

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
