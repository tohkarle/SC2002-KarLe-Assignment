package cams.manager;

import java.util.Stack;

import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.interfaces.ViewHandler;
import cams.view.root.RootView;
import cams.view.user.UserOptionsView;

/**
 * RootController is a class that implements the Navigation interface and manages the navigation flow of views in the application.
 * It maintains a stack of views and uses a NavigationManager to fetch and display views based on user interactions.
 *
 * @author Toh Kar Le
 */
public class NavigationManager implements Navigation, ViewHandler {

    private Stack<View> views;
    private View rootView;

    public NavigationManager() {
        this.views = new Stack<>();
        this.rootView = new RootView(this);
    }

    @Override
    public void displayView() {

        while (!views.isEmpty()) {
            clearTerminal();
            System.out.println("\nWelcome to the Camp Application and Management System");
            View view = views.peek();
            view.render();
        }
    }

    @Override
    public void initializeView() {
        views.push(rootView);
    }

    @Override
    public void navigateTo(View view) {
        views.push(view);
    }

    @Override
    public void dismissView() {
        views.pop();
    }

    @Override
    public void terminate() {
        clearTerminal();
        views.clear();
    }

    @Override
    public void popToRoot() {
        views.clear();
        views.push(rootView);
        views.push(new UserOptionsView(this));
    }

    @Override
    public Class<?> getPreviousView() {
        if (views.size() < 2) {
            return null;
        }
        return views.get(views.size() - 2).getClass();
    }

    private void clearTerminal() {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
    }
}
