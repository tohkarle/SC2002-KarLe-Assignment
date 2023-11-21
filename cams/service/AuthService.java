package cams.service;

import java.util.HashMap;

import cams.model.Staff;
import cams.model.Student;
import cams.model.User;
import cams.utils.Serialize;

// Create user
// Log user in
// Return userID once logged in
// Log user out
public class AuthService {

    private HashMap<String, User> userMap;

    public AuthService() {
        Serialize.checkAndCreateFile("userMap.sav");
        this.load();
    }

    public User registerUser(String email, String name, String password, String faculty, boolean isStaff) {
        User newUser;

        if (isStaff) {
            newUser = new Staff(name, email, password, faculty);
        } else {
            newUser = new Student(name, email, password, faculty);
        }
        
        userMap.put(name, newUser);
        return newUser;
    }

    public User getUser(String userName) {
        return new User(userMap.get(userName));
    }

    public Boolean isValidUser(String email){
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User logIn(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public Boolean checkPassword(String email, String password){
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user.passwordMatches(password);
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

    public boolean nameAlreadyExists(String name) {
        for (User user : userMap.values()) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void updatePassword(String userName, String newPassword) {
        userMap.get(userName).setPassword(newPassword);
        save();
    }

    public void save() {
        Serialize.save("userMap.sav", userMap);
    }

    public void load() {
        try {
            @SuppressWarnings("unchecked")
            HashMap<String, User> loadedMap = (HashMap<String, User>) Serialize.load("userMap.sav");
            if (loadedMap != null) {
                userMap = loadedMap;
            } else {
                userMap = new HashMap<>();
            }
            // userMap = loadedMap;
        } catch (Exception e) {
            userMap = new HashMap<>();
        }
    }
}
