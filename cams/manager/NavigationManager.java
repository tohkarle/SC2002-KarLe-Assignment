package cams.manager;

import java.util.Stack;

import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.view.root.RootView;
import cams.view.user.UserOptionsView;

/**
 * RootController is a class that implements the Navigation interface and manages the navigation flow of views in the application.
 * It maintains a stack of views and uses a NavigationManager to fetch and display views based on user interactions.
 *
 * @author Toh Kar Le
 */
public class NavigationManager implements Navigation {

    private static NavigationManager instance;
    private Stack<View> views;
    private View rootView;

    public NavigationManager() {
        this.views = new Stack<>();
    }

    public static NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }

    public void displayView() {
        while (!views.isEmpty()) {
            // for (View view : views) {
            //     System.out.println(view);
            // }
            View view = views.peek();
            view.render();
        }
    }

    public void initializeRootView() {
        rootView = new RootView(this);
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
    public void popToRoot() {
        views.clear();
        views.push(rootView);
        views.push(new UserOptionsView(this));
    }

    @Override
    public Class<?>  getPreviousView() {
        if (views.size() < 2) {
            return null;  // No previous view
        }
        return views.get(views.size() - 2).getClass();
    }
}
