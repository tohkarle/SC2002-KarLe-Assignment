package cams.manager;

import java.util.Stack;

import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.service.NavigationService;

/**
 * RootController is a class that implements the Navigation interface and manages the navigation flow of views in the application.
 * It maintains a stack of views and uses a NavigationManager to fetch and display views based on user interactions.
 *
 * @author Toh Kar Le
 */
public class NavigationManager implements Navigation {

    private Stack<View> views;
    private NavigationService service;

    public NavigationManager(NavigationService service) {
        this.views = new Stack<>();
        this.service = service;
    }

    public void displayView() {
        while (!views.isEmpty()) {
            for (View view : views) {
                System.out.println(view);
            }
            View view = views.peek();
            view.render();
        }
    }

    public void initializeRootView() {
        views.push(this.service.getView("root.RootView"));
    }

    @Override
    public void navigateTo(String viewName) {
        views.push(service.getView(viewName));
    }

    @Override
    public void dismissView() {
        views.pop();
    }

    @Override
    public void popToRoot() {
        views.clear();
        views.push(this.service.getView("root.RootView"));
    }
}
