package edu.upc.eetac.dsa;


import java.util.List;

/**
 * Created by OriolGresa on 22/11/16.
 */
public interface InterfaceJava {

    void addUser (User user);
    void updateUser (User user, String name, String pass);
    void getUser (String name);
    void listUsersOrderedByName();
    void addEtakemonToUser(Etakemon etakemon, User user);

}
