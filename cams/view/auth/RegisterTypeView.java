package cams.view.auth;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.utils.Dismiss;

public class RegisterTypeView extends View {

    private boolean isStaff;

    // Options for this view:
    private Options userOptions;

    // No UI for this view

    public RegisterTypeView(Navigation navigation) {
        super(navigation);
        isStaff = false;
    }

    public void render() {
        // Choose to register as staff or student
        userOptions = super.getOptions("auth.UserOptions");
        userOptions.display("Do you want to register as a Staff or a Student?");
        int option = userOptions.selection();

        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView(); 
            return;
        }

        isStaff = (option == 1);
        super.getNavigation().navigateTo("auth.RegisterView");
    }

    public boolean getIsStaff() {
        return this.isStaff;
    }
}
