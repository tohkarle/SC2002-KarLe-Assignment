package cams.service;

import java.util.HashMap;

import cams.model.Staff;
import cams.model.Student;
import cams.model.User;
import cams.utils.Serialize;
import cams.utils.UniqueKey;

// Create user
// Log user in
// Return userID once logged in
// Log user out
public class AuthService {

    private static int uniqueKey = 0;
    private static HashMap<Integer, User> userMap;

    public AuthService(){
        Serialize.checkAndCreateFile("UserManagerKey.sav");
        Serialize.checkAndCreateFile("userMap.sav");
        this.load();
    }

    public int registerUser(String email, String name, String password, String faculty, boolean isStaff) {
        User newUser;
        uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        while(userMap.get(uniqueKey) != null) uniqueKey = UniqueKey.generateNewKey(uniqueKey);

        if (isStaff) {
            newUser = new Staff(uniqueKey, email, name, password, faculty);
        } else {
            newUser = new Student(uniqueKey, email, name, password, faculty);
        }
        
        userMap.put(uniqueKey, newUser);
        return uniqueKey;
    }

    public Boolean isValidUser(String email){
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public int logIn(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user.getUserID();
            }
        }
        return -1;
    }

    public Boolean checkPassword(String email, String password){
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user.checkPassword(password);
            }
        }
        return false;
    }

    public boolean emailAlreadyExists(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(int userID) {
        User user = userMap.get(userID);
        if (user instanceof Student) {
            return new Student(user);
        } else if (user instanceof Staff) {
            return new Staff(user);
        }
        return null;
    }

    public void save() {
        Serialize.save("UserManagerKey.sav", uniqueKey);
        Serialize.save("userMap.sav", userMap);
    }

    public void load() {
        try {
            uniqueKey = (Integer)Serialize.load("UserManagerKey.sav");
            @SuppressWarnings("unchecked")
            HashMap<Integer, User> loadedMap = (HashMap<Integer, User>)Serialize.load("userMap.sav");
            userMap = loadedMap;
        } catch (Exception e) {
            uniqueKey = 0;
            userMap = new HashMap<>();
        }
    }

    public String getName(int userID){
        return ((User)userMap.get(userID)).getName();
    }
}
