package cams.interfaces;

public interface Navigation {
    void navigateTo(String viewName);
    void dismissView();
    void popToRoot();
}
