package cams.option.camp;

import java.util.Arrays;

import cams.manager.CampManager;
import cams.manager.UserManager;

public class RegisteredCampInfoOptions extends CampInfoOptions {

    private UserManager userManager;

    public RegisteredCampInfoOptions(CampManager campManager, UserManager userManager) {
        super(campManager);
        this.userManager = userManager;
    }

    @Override
    public void updateCampInfo() {
        super.getCampManager().createTempCamp();
        super.setCamp(super.getCampManager().getSelectedCamp());
        super.setCampInfo();
        this.setCampInfo();
    }

    @Override
    public void setCampInfo() {
        if (super.getCampManager().isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), super.getCampManager().getSelectedID())) {
            // Committee member can suggest edit for camp in the registered camp view
            // It will be a view-only display for the camp info so we will need to add number here
            super.getOptions().addAll(Arrays.asList(
                String.format("(%d) Raise suggestion", 1),
                String.format("(%d) Manage your suggestions", 2)
            ));
        } else {
            // Attendee can withdraw from camp in the registered camp view
            // It will be a view-only display for the camp info so we will need to add number here
            super.getOptions().addAll(Arrays.asList(
                String.format("(%d) Withdraw from camp", 1)
            ));
        }
    }
}
