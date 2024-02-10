package model;

import java.util.LinkedHashMap;

public class User extends Records {
    private String name;
    private Records myUserRecords;

    public User(String name) {
        this.name = name;
        myUserRecords = new Records();
    }

    public String getName() {
        return name;
    }

    public Records getMyUserRecords() {
        return myUserRecords;
    }


}
