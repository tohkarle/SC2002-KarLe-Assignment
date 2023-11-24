package cams.view.root;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.auth.AuthOptions;
import cams.utils.LoadingIndicator;
import cams.view.auth.LogInView;
import cams.view.auth.RegisterTypeView;

/**
 * View object for Root page, the first page of the application
 */
public class RootView implements View {

    private Navigation navigation;

    /**
     * Initialize the RootView
     * @param navigation Navigation object used to control navigation of the application
     */
    public RootView(Navigation navigation) {
        this.navigation = navigation;
    }

    /**
     * Render the RootView
     */
    public void render() {

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
                navigation.terminate();
                break;
        }
    }
}
