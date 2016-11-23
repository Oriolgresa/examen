/**
 * Created by OriolGresa on 22/11/16.
 */
public interface InterfaceJava {

    void addUser(User user);
    void updateUser(User user, String name, String pass);
    User getUser(String name);
    void listUsersOrderedByName();
    void addEtakemonToUser(Etakemon etakemon, User user);
    void getEtakemonsFromUserByInsertion(User user);

}
