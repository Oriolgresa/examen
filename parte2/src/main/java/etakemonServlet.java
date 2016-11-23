import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by OriolGresa on 23/11/16.
 */
@WebServlet(name = "etakemonServlet", urlPatterns = "/etakemon")
public class etakemonServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario= request.getParameter("usuario");
        String pass= request.getParameter("pass");
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        User user = new User(usuario,pass);

        Etakemon etak = new Etakemon(nombre, id);
        Controller.getInstance().addEtakemonToUser(etak, user);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getQueryString();
        User u = Controller.getInstance().getUser(usuario);
        if(u.getEtakemonList() == null){
            response.getWriter().write("sense etakemons");
        } else {
            String etakemons = "";
            for(int i = 0; i<u.getEtakemonList().size(); i++){
                etakemons += u.getEtakemonList().get(i).getName() + "," + u.getEtakemonList().get(i).getId() + ":";
            }
            response.getWriter().write(etakemons);

        }
    }
}


