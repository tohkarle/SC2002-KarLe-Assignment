package cams.manager;

import cams.model.User;

public class UserManager {

    private User currentUser;

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // public String getName(int userID){
    //     return ((User)userMap.get(userID)).getName();
    // }

    // public String getEmail(int userID){
    //     return ((User)userMap.get(userID)).getEmail();
    // }

    // public String getFaculty(int userID){
    //     return ((User)userMap.get(userID)).getFaculty();
    // }

    // public Boolean hasDefaultPassword(int userID){
    //     return ((User)userMap.get(userID)).checkPassword("password");
    // }

    // public boolean passwordIsCorrect(int userID, String password) {
    //     return ((User)userMap.get(userID)).checkPassword(password);
    // }

    // public void setPassword(int userID, String password){
    //     ((User)userMap.get(userID)).setPassword(password);
    // }

    // public void addToUserCampIDs(int userID, int campID) {
    //     ((User)userMap.get(userID)).addToCampIDsList(campID);
    // }

    // public void removeFromUserCampIDs(int userID, int campID) {
    //     ((User)userMap.get(userID)).removeCamp(campID);
    // }
}