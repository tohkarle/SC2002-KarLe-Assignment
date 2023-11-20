package cams.interfaces;

public interface Navigation {
    // void navigateTo(String viewName);
    void navigateTo(View view);
    void dismissView();
    void popToRoot();
    Class<?> getPreviousView();
}
