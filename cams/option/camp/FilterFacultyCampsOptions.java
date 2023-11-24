package cams.option.camp;

/**
 * Options object for selecting camp filter in the FilterFacultyCampsView
 */
public class FilterFacultyCampsOptions extends FilterAllCampsOptions {

    /**
     * Initialize the camp filter options
     */
    public FilterFacultyCampsOptions() {
        super.getOptions().remove("Faculty");
    }
}
