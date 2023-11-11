package cams.model;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int userID;
    private String email;
    private String name;
    private String password;
    private String faculty;
    private ArrayList<Integer> campIDs;
    
    public User(int userID, String email, String name,String password, String faculty){
        this.userID = userID;
        this.email = email;
        this.name = name;
        this.password = password;
        this.faculty = faculty;
        this.campIDs = new ArrayList<Integer>();
    }

    public void addToCampIDsList(int campID){
        this.campIDs.add(campID);
    }

    public void removeCamp(int campID){
        this.campIDs.remove(campID);
    }   

    public ArrayList<Integer> getCampList(){
        return this.campIDs;
    }

    public int getUserID() {
        return this.userID;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public Boolean checkPassword(String password){
        return (this.password.equals(password));
    }

    public void setPassword(String password){
        this.password = password;
    }
}
