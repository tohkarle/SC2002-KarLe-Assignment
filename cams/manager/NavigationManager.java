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
    private NavigationService navigationService;

    public NavigationManager(NavigationService navigationService) {
        this.views = new Stack<>();
        this.navigationService = navigationService;
    }

    public void displayView() {
        while (!views.isEmpty()) {
            for (View view : views) {
                System.out.println(view);
            }
            getPreviousView();
            View view = views.peek();
            view.render();
        }
    }

    public void initializeRootView() {
        views.push(this.navigationService.getView("root.RootView"));
    }

    @Override
    public void navigateTo(String viewName) {
        views.push(navigationService.getView(viewName));
    }

    @Override
    public void dismissView() {
        views.pop();
    }

    @Override
    public void popToRoot() {
        views.clear();
        views.push(this.navigationService.getView("root.RootView"));
    }

    @Override
    public String getPreviousView() {
        if (views.size() < 2) {
            return null;  // No previous view
        }
        String name = navigationService.getViewName(views.get(views.size() - 2));
        System.out.println("Previous view: " + name);
        return name;
    }
}
