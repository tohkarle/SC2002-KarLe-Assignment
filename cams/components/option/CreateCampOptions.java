package cams.components.option;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateCampOptions extends ViewOnlyOptions {
    public CreateCampOptions() {
        super.setOptions( new ArrayList<String>(Arrays.asList(
                "Ente camp name: ",
                "Enter faculty: ",
                "Enter visibility",
                "Enter start date (yyyy-MM-dd): ",
                "Enter end date (yyyy-MM-dd): "
            ))
        );
    }
}
