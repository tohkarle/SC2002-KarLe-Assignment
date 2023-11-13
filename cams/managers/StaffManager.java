package cams.managers;

import java.util.ArrayList;

import cams.models.Staff;

public class StaffManager extends UserManager {
    public ArrayList<Integer> getCreatedCampIDs(int staffID) {
        return ((Staff)userMap.get(staffID)).getCampList();
    }
}
