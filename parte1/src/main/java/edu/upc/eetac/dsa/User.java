package edu.upc.eetac.dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OriolGresa on 22/11/16.
 */
public class User {
    public String name, password;
    public List<Etakemon> etakemonList;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.etakemonList = new ArrayList<Etakemon>();
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

    public List<Etakemon> getEtakemonList() {
        return etakemonList;
    }

    public void setEtakemonList(List<Etakemon> etakemonList) {
        this.etakemonList = etakemonList;
    }

    public void addEtakemon(Etakemon etakemon){
            this.etakemonList.add(etakemon);
    }
}
