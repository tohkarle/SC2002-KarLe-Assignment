package cams.option.camp;

public class FilterFacultyCampsOptions extends FilterAllCampsOptions {
    public FilterFacultyCampsOptions() {
        super.getOptions().remove("Faculty");
    }
}
