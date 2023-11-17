package cams.view;

import cams.components.option.Options;
import cams.interfaces.InputField;
import cams.interfaces.View;

public class TestView extends View {
    
    @Override
    public void render() {
        System.out.println("Hello world\n");

        Options options = super.getOptions().get(0);
        for (String option : options.getOptions()) {
            System.out.println(option);
        }

        InputField inputField = super.getUIs().get(0);
        if (!inputField.focused()) { super.dismiss(); };
    }
}
