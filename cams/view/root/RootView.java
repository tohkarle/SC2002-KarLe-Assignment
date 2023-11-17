package cams.view.root;

import cams.components.LoadingIndicator;
import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;

public class RootView extends View {

    // Options for this view:
    private Options authOptions;

    // No UI for this view

    public RootView(Navigation navigation) {
        super(navigation);
    }

    public void render() {

        System.out.println("\nWelcome to the Camp Application and Management System");

        authOptions = super.getOptions("auth.AuthOptions");
        authOptions.display("Would you like to register or log in?");
        int option = authOptions.selection();

        switch(option) {
            case 1:
                super.getNavigation().navigateTo("auth.RegisterTypeView");
                break;
            case 2:
                super.getNavigation().navigateTo("auth.LogInView");
                break;
            case 3:
                LoadingIndicator.terminateAppLoadingIndicator();
                super.getNavigation().dismissView();
                break;
        }
    }
}
