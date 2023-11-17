package cams.interfaces;

import java.util.HashMap;

import cams.components.option.Options;

public abstract class View {

    private Navigation navigation;
    private HashMap<String, Options> options;
    private HashMap<String, UI> uis;

    public View() {
        this.navigation = null;
        this.options = new HashMap<>();
        this.uis = new HashMap<>();
    }

    public View (Navigation navigation) {
        this.navigation = navigation;
        this.options = new HashMap<>();
        this.uis = new HashMap<>();
    }

    public abstract void render();

    public Navigation getNavigation() {
        return this.navigation;
    }

    public Options getOptions(String optionsName) {
        return this.options.get(optionsName);
    }

    public UI getUI(String uiName) {
        return this.uis.get(uiName);
    }

    public void setOptions(HashMap<String, Options> options) {
        this.options = new HashMap<>(options);
        for (String optionsName : this.options.keySet()) {
            System.out.println("Created options: " + optionsName);
        }
    }

    public void setUIs(HashMap<String, UI> uis) {
        this.uis = new HashMap<>(uis);
        for (String uiName : this.uis.keySet()) {
            System.out.println("Created ui: " + uiName);
        }
    }

    public void dismiss() {
        navigation.dismissView();
    }
}