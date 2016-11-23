import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by OriolGresa on 22/11/16.
 */
public class Controller implements InterfaceJava {

    static final Logger logger = Logger.getLogger(Controller.class);
    private static Controller instance;
    private HashMap<String,User> UserList;
    private Controller(){
        UserList = new HashMap<String, User>();
    }
    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }
    public void addUser(User user){
        UserList.put(user.getName(), user);
        logger.info("Usuario " + user.getName()+ " añadido con password " + user.getPassword());
        logger.info("hay " + UserList.size() + " usuario/s");
    }

    public void updateUser (User user, String name, String pass){

        if (user.getName().equals(name)){
            user.setPassword(pass);
            User newUser = new User(user.getName(),user.getPassword());
            UserList.remove(user.getName());
            UserList.put(newUser.getName(), newUser);
            logger.info("Usuario modificado. El nuevo password es " + user.getPassword());
        }else {
            logger.error("El usuario que quiere modificar no existe.");
        }
    }

    public User getUser(String name) {

        String nombre = UserList.get(name).getName();
        String password = UserList.get(name).getPassword();
        List<Etakemon> etakemons = UserList.get(name).getEtakemonList();
        User user = new User(nombre,password);
        if (etakemons.size() == 0) {
            logger.info("nombre: " + nombre + ", password: " + password + ", etakemons: " + etakemons);
        } else {
            StringBuffer mensajeParaMostrar = new StringBuffer("nombre: " + nombre + ", password: " + password + ", etakemons: [");

            for (int i = 0; i < etakemons.size(); i++) {
                if (i != etakemons.size()-1)
                    mensajeParaMostrar.append("nombre: "+ etakemons.get(i).getName() + ", id: " +etakemons.get(i).getId() + "; ");
                else
                    mensajeParaMostrar.append("nombre: "+ etakemons.get(i).getName() + ", id: " +etakemons.get(i).getId() +"]");

            }
            logger.info(mensajeParaMostrar);
        }
        return user;
    }

    public void listUsersOrderedByName() {

        List<String> list = new ArrayList<String>();
        Iterator iterator = UserList.keySet().iterator();
        while (iterator.hasNext()){
            String name = iterator.next().toString();
            list.add(name);
        }
        logger.info("Lista usuarios ordenados alfabeticamente: ");
        Collections.sort(list);
        for (int i=0;i<list.size();i++){
            logger.info("nombre: " + UserList.get(list.get(i)).getName() +
                        ", password: " + UserList.get(list.get(i)).getPassword());
        }
    }

    public void addEtakemonToUser(Etakemon etakemon, User user){

        User usuarioNuevo = UserList.get(user.getName());
        usuarioNuevo.addEtakemon(etakemon);
        UserList.remove(user.getName());
        user= usuarioNuevo;
        UserList.put(user.getName(),user);
        logger.info("el etakemon " + etakemon.getName() + " se ha añadido al usuario " +user.getName());
    }

    public void getEtakemonsFromUserByInsertion(User user){

        List<Etakemon> lista= UserList.get(user.getName()).getEtakemonList();
        logger.info("Lista etakemons del usuario " + user.getName());
        for(int i=0;i<lista.size();i++){
            logger.info("nombre: " + lista.get(i).getName() + ", id: " + lista.get(i).getId());

        }

    }

}
