package cams.manager;

import java.util.ArrayList;

import cams.model.Staff;

public class StaffManager extends UserManager {
    public ArrayList<Integer> getCreatedCampIDs(int staffID) {
        return ((Staff)userMap.get(staffID)).getCampList();
    }
}
