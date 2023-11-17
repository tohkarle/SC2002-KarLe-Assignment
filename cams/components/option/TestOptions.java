package cams.components.option;

import java.util.ArrayList;
import java.util.Arrays;

public class TestOptions extends Options {
    public TestOptions() {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Hello World 1"
            ))
        );
    }
}
