public class App
{

    public static void main(String[] args)
    {

        User user = new User("oriol","1234");
        Controller.getInstance().addUser(user);

    }
}
