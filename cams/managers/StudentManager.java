package cams.managers;

import cams.models.Student;

public class StudentManager extends UserManager {
    public int getPoint(int studentID){
        return ((Student)userMap.get(studentID)).getPoint();
    }
}
