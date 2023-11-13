package cams.components.option;

import java.util.Arrays;

public class CampInfoOptionsForEditing extends CampInfoOptions {

    public CampInfoOptionsForEditing(int campID) {
        super(campID);
        super.options.remove(String.format("Staff-in-charge: %s", this.staffInCharge));
        super.options.addAll(Arrays.asList(
            "Update changes",
            "Manage Enquiries",
            "Manage Suggestions",
            "Create report",
            "Delete Camp"
        ));
    }
}
