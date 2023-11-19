package cams.option.camp;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.ViewOnlyOptions;

public class EditCampInfoOptions extends ViewOnlyOptions {

    public EditCampInfoOptions() {
        super.setOptions( 
                new ArrayList<String>(Arrays.asList(
                "Edit name: ",
                "Edit faculty: ",
                "Edit Location: ",
                "Edit description: ",
                "Edit visibility",
                "Edit start date (yyyy-MM-dd): ",
                "Edit end date (yyyy-MM-dd): ",
                "Edit registration closing date: ",
                "Edit total slots: ",
                "Edit total comittee slots: ",
                "Manage Enquiries: ",
                "Manage Suggestions: ",
                "Create report: ",
                "Delete Camp: "
            ))
        );
    }
}
