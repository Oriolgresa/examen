package edu.upc.eetac.dsa;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException {
        User user1 = new User("oriol","3456");
        User user2 = new User("lola","344444");
        //user1.insertUser();
        //user2.insertUser();
        user1.updateUser();
        //user1.deleteUser();
        List<User> l = UserDAO.getAllUsers();
    }
}
