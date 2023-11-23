package cams.view.user;

import cams.components.input.GetInput;
import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.UserManager;
import cams.option.user.StaffOptions;
import cams.option.user.StudentOptions;
import cams.utils.LoadingIndicator;
import cams.view.camp.AllCampsView;
import cams.view.camp.CreateCampView;
import cams.view.camp.CreatedCampsView;
import cams.view.camp.FacultyCampsView;
import cams.view.camp.RegisterForCampView;
import cams.view.camp.RegisteredCampsView;

public class UserOptionsView implements View {

    private Navigation navigation;
    private UserManager userManager;

    public UserOptionsView(Navigation navigation) {
        this.navigation = navigation;
        this.userManager = UserManager.getInstance();
    }

    @Override
    public void render() {
        
        if (userManager.getCurrentUser() == null) { 
            navigation.dismissView();
            return; 
        }

        Input getInput = new GetInput();

        View[] staffViews = new View[] {
            new ProfileView(navigation),
            new ChangePasswordView(navigation, getInput),
            new AllCampsView(navigation),
            new CreateCampView(navigation, getInput),
            new CreatedCampsView(navigation, getInput),
        };

        View[] studentViews = new View[] {
            new ProfileView(navigation),
            new ChangePasswordView(navigation, getInput),
            new FacultyCampsView(navigation),
            new RegisteredCampsView(navigation, getInput),
            new RegisterForCampView(navigation, getInput),
        };

        // Check if user is authenticated
        if (!userManager.isAuthenticated()) {
            System.out.println("You have to register or log in first");
            navigation.dismissView();
            return;
        }

        if (userManager.isStaff()) {
            
            // Display staff's options and let staff select action
            Options staffOptions = new StaffOptions();
            staffOptions.display("Choose your option: ");
            int option = staffOptions.selection();

            // Display corresponding view
            if (option <= staffViews.length) {
                navigation.navigateTo(staffViews[option - 1]);
                return;
            } else {
                logOut();
                return;
            }

        } else {

            // Display student's options and let student select action
            Options studentOptions = new StudentOptions();
            studentOptions.display("Choose your option: ");
            int option = studentOptions.selection();

            // Display corresponding view
            if (option <= studentViews.length) {
                navigation.navigateTo(studentViews[option - 1]);
                return;
            } else {
                logOut();
                return;
            }
        }
    }

    private void logOut() {
        LoadingIndicator.logOutLoadingIndicator();
        userManager.setCurrentUser(null);
        navigation.popToRoot();
    }
}
