package cams.view.root;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.auth.AuthOptions;
import cams.utils.LoadingIndicator;
import cams.view.auth.LogInView;
import cams.view.auth.RegisterTypeView;

public class RootView implements View {

    private Navigation navigation;

    public RootView(Navigation navigation) {
        this.navigation = navigation;
    }

    public void render() {

        System.out.println("\nWelcome to the Camp Application and Management System");

        Options authOptions = new AuthOptions();
        authOptions.display("Would you like to register or log in?");
        int option = authOptions.selection();

        switch(option) {
            case 1:
                navigation.navigateTo(new RegisterTypeView(navigation));
                break;
            case 2:
                navigation.navigateTo(new LogInView(navigation));
                break;
            case 3:
                LoadingIndicator.terminateAppLoadingIndicator();
                navigation.dismissView();
                break;
        }
    }
}
