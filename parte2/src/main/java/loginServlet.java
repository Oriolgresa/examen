import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by OriolGresa on 23/11/16.
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user1 = new User("oriol","1234");
        Controller.getInstance().addUser(user1);
            String usr = request.getParameter("user");
            String password = request.getParameter("password");
        System.out.print(usr);
        System.out.print(password);
            User user = Controller.getInstance().getUser(usr);
            if(user!= null) {
                if(user.getPassword().equals(password)){
                    request.getRequestDispatcher("/etakemon.jsp").forward(request,response);
                }
            }
    }
}


