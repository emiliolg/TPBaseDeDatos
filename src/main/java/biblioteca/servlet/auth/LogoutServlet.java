package biblioteca.servlet.auth;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"logout"})
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        // solo limpiamos la session y vamos al login
        req.getSession().invalidate();
        req.setAttribute("msg", "Logout ok!");
        resp.sendRedirect("/login");
    }
}