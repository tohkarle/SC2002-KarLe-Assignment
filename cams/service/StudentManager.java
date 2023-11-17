package cams.service;

import cams.manager.UserManager;
import cams.model.Student;

public class StudentManager extends UserManager {
    public int getPoint(int studentID){
        return ((Student)userMap.get(studentID)).getPoint();
    }
}
