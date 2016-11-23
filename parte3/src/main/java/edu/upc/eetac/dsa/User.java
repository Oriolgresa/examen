package edu.upc.eetac.dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OriolGresa on 23/11/16.
 */
public class User extends UserDAO{
    public String name, password;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
