package cams.option.camp;

import cams.manager.UserManager;

public class FilterUserCampsOptions extends FilterAllCampsOptions {
    public FilterUserCampsOptions() {
        UserManager userManager = UserManager.getInstance();
        if (userManager.isStaff()) {
            super.getOptions().remove("Staff-in-charge");
        } else {
            super.getOptions().remove("Faculty");
        }
    }
}
