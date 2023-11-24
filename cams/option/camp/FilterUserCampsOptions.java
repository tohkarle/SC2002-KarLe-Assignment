package cams.option.camp;

import cams.manager.UserManager;

/**
 * Options object for selecting camp filter in the FilterCreatedCampsView
 */
public class FilterUserCampsOptions extends FilterAllCampsOptions {

    /**
     * Initialize the camp filter options
     */
    public FilterUserCampsOptions() {
        UserManager userManager = UserManager.getInstance();
        if (userManager.isStaff()) {
            super.getOptions().remove("Staff-in-charge");
        } else {
            super.getOptions().remove("Faculty");
        }
    }
}
