package cams.view.auth;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.auth.UserOptions;
import cams.utils.Dismiss;

/**
 * View object for Register Type page
 */
public class RegisterTypeView implements View {

    private Navigation navigation;

    /**
     * Initialize the RegisterTypeView
     * @param navigation
     */
    public RegisterTypeView(Navigation navigation) {
        this.navigation = navigation;
    }


    /**
     * Render the RegisterTypeView
     */
    public void render() {

        // Choose to register as staff or student
        Options userOptions = new UserOptions();
        userOptions.display("Do you want to register as a Staff or a Student?");
        int option = userOptions.selection();

        if (option == Dismiss.intOption()) { 
            navigation.dismissView(); 
            return;
        }

        boolean isStaff = (option == 1);
        navigation.navigateTo(new RegisterView(navigation, isStaff));
    }
}
