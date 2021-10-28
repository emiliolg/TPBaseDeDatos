package biblioteca.servlet;

import biblioteca.dao.DB;
import biblioteca.model.Trip;
import biblioteca.model.User;
import biblioteca.servlet.auth.LoginServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/* Homeservlet se encarga de fijarse si el usuario esta logeado en la sesion o no
 *
 */
@WebServlet({""})
public class HomeServlet extends HttpServlet {

    // llamado por el server de jetty cuando hago un GET a /
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // me fijo si hay un user en la session, para ver si ya hizo el login
        final User loginUser = LoginServlet.getLoginUser(req);
        if (loginUser == null) {
            resp.sendRedirect("/login");
        } else {
            //Muetsra la lista de viajes del chofer
            final int dni = loginUser.getDni();
            final List<Trip> myTrips = DB.getInstance().getTripDAO().listDriverTrips(dni);
            req.setAttribute("myTrips", myTrips);
            req.getRequestDispatcher("/index.jsp").include(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // todo aca tenemos que marcar un viaje como emepezado o finalizado
        // esto nos viene de los post de los forms en index.jsp

        Boolean started = Boolean.valueOf(req.getParameter("started"));
        Boolean ended = Boolean.valueOf(req.getParameter("ended"));


    }
}

