package cams.option.camp;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.model.Camp;

/**
 * Options object for containing selections in the RegisteredCampInfoView
 * These options include managing enquiries, giving suggestions, managing suggestions, and generating registration report, for committee members
 */
public class RegisteredCampInfoOptions extends CampInfoOptions {

    UserManager userManager;
    CampManager campManager;

    /**
     * Initialize the camp information options
     * @param camp The selected Camp object
     */
    public RegisteredCampInfoOptions(Camp camp) {
        super(camp);
        this.userManager = UserManager.getInstance();
        this.campManager = CampManager.getInstance();
        this.changeOption();
    }

    public void changeOption() {
        if (campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), super.getCamp().getId())) {
            // Committee member can suggest edit for camp in the registered camp view
            // It will be a view-only display for the camp info so we will need to add number here
            super.getOptions().addAll(Arrays.asList(
                String.format("(%d) Manage enquiries", 1),
                String.format("(%d) Give suggestion", 2),
                String.format("(%d) Manage your suggestions", 3),
                String.format("(%d) Generate registration report", 4)
            ));
        } else {
            // Attendee can withdraw from camp in the registered camp view
            // It will be a view-only display for the camp info so we will need to add number here
            super.getOptions().addAll(Arrays.asList(
                String.format("(%d) Raise enquiry", 1),
                String.format("(%d) Manage your enquiries", 2),
                String.format("(%d) Withdraw from camp", 3)
            ));
        }
    }

    @Override
    public int selection() {
        if (campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), super.getCamp().getId())) {
            IntInput selection = new GetSelectionWithDismiss(1, 4);
            return selection.getValidInt("Your selection: ");
        } else {
            IntInput selection = new GetSelectionWithDismiss(1, 3);
            return selection.getValidInt("Your selection: ");
        }
    }
}
