package cams.controller;

import java.io.IOException;
import java.util.Stack;

import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.interfaces.ViewHandler;
import cams.utils.AsciiArt;
import cams.view.root.RootView;
import cams.view.user.UserOptionsView;

/**
 * RootController is a class that implements the Navigation interface and
 * manages the navigation flow of views in the application.
 * It maintains a stack of views and uses a NavigationManager to fetch and
 * display views based on user interactions.
 *
 * @author Toh Kar Le
 */
public class NavigationController implements Navigation, ViewHandler {

    /**
     * A stack of View objects that signify the view hierarchy
     */
    private Stack<View> views;

    /**
     * A View object that is the root view
     */
    private View rootView;

    public NavigationController() {
        this.views = new Stack<>();
        this.rootView = new RootView(this);
    }

    /**
     * The method to display the view
     */
    @Override
    public void displayView() {
        while (!views.isEmpty()) {
            clearTerminal();
            // System.out.println("\nWelcome to the Camp Application and Management
            // System");
            AsciiArt.printCams();
            View view = views.peek();
            view.render();
        }
        clearTerminal();
    }

    /**
     * A method to initialize the root view
     */
    @Override
    public void initializeView() {
        views.push(rootView);
    }

    /**
     * A method to change the current view
     */
    @Override
    public void navigateTo(View view) {
        views.push(view);
    }

    /**
     * A method to go back to the previous view layer
     */
    @Override
    public void dismissView() {
        views.pop();
    }

    /**
     * A method to go back to the root view
     */
    @Override
    public void popToRoot() {
        views.clear();
        views.push(rootView);
        views.push(new UserOptionsView(this));
    }

    /**
     * A method to flush the screen
     */
    private void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Unable to clear terminal. Error: " + ex.getMessage());
        }
    }
}
