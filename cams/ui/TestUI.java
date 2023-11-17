package cams.ui;

import cams.components.input.GetString;
import cams.components.option.Options;
import cams.interfaces.InputField;
import cams.utils.Dismiss;

public class TestUI extends GetString implements InputField {

    private Options options;
    private String name;

    public TestUI(Options options) {
        super("Test: ");
        this.options = options;
        this.name = "";
    }

    @Override
    public boolean focused() {
        name = super.getValidString();
        if (name.equals(Dismiss.stringOption())) { return false; }
        options.addOption(name);
        for (String option : options.getOptions()) {
            System.out.println(option);
        }
        return true;
    }
}