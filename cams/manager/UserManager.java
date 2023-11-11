package cams.manager;

import cams.model.User;

public class UserManager extends AuthManager {

    public String getName(int userID){
        return ((User)userMap.get(userID)).getName();
    }

    public String getEmail(int userID){
        return ((User)userMap.get(userID)).getEmail();
    }

    public String getFaculty(int userID){
        return ((User)userMap.get(userID)).getFaculty();
    }

    public Boolean hasDefaultPassword(int userID){
        return ((User)userMap.get(userID)).checkPassword("password");
    }

    public void setPassword(int userID, String password){
        ((User)userMap.get(userID)).setPassword(password);
    }

    public void addToUserCampIDs(int userID, int campID) {
        ((User)userMap.get(userID)).addToCampIDsList(campID);
    }

    public void removeFromUserCampIDs(int userID, int campID) {
        ((User)userMap.get(userID)).removeCamp(campID);
    }
}