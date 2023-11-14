package cams.components.option;

import java.util.ArrayList;
import java.util.Arrays;

public class EditCampOptions extends Options {

    public EditCampOptions() {
        super.options = new ArrayList<String>(Arrays.asList(
            "Edit name: ",
            "Edit faculty: ",
            "Edit Location: ",
            "Edit description: ",
            "Edit visibility",
            "Edit registration closing date: ",
            "Edit start date (yyyy-MM-dd): ",
            "Edit end date (yyyy-MM-dd): ",
            "Edit total slots: ",
            "Edit total comittee slots: ",
            "Manage Enquiries: ",
            "Manage Suggestions: ",
            "Create report: ",
            "Delete Camp: "
        ));
    }
}
