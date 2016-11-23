package edu.upc.eetac.dsa;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        User user = new User("oriol","1234");
        User user1 = new User("lola","lolita");
        Controller.getInstance().addUser(user1);
        Controller.getInstance().updateUser(user,"oriol","334456");
        Controller.getInstance().getUser("oriol");
        Controller.getInstance().listUsersOrderedByName();
        Etakemon etakemon1 = new Etakemon("pikachu",1);
        Etakemon etakemon2 = new Etakemon("chorizor",2);
        Controller.getInstance().addEtakemonToUser(etakemon1,user);
        Controller.getInstance().addEtakemonToUser(etakemon2,user);
        Controller.getInstance().getUser("oriol");
        Controller.getInstance().getEtakemonsFromUserByInsertion(user);
        Controller.getInstance().listUsersOrderedByName();

    }
}
