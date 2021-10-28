package biblioteca.servlet.auth;

import biblioteca.dao.DB;
import biblioteca.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"login"})
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // usamos el jsp del login cuando hacen un GET
        req.getRequestDispatcher("/auth/login.jsp").include(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // agarramos user y password que se mandaron en el form de login.jsp
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // buscamos el usuario en la DB
        User user = DB.getInstance().getUserDAO().find(username);

        // nos fijamos si esta bien la password
        if (user != null && password.equals(user.getPassword())) {
            // esta ok, creamos la session
            HttpSession session = req.getSession(true);
            // guardamos el usuario en la session
            session.setAttribute("user", user);
            // que expire nunca
            session.setMaxInactiveInterval(Integer.MAX_VALUE);
            // vamos al home
            resp.sendRedirect("/");
        } else {
            // esta mal, nos quedamos en el login con un mensaje de error
            req.setAttribute("msg", "Usuario o password incorrectos!");
            req.getRequestDispatcher("/auth/login.jsp").include(req, resp);
        }
    }

    //devuelve el user si es q ya esta logeado en la sesion, sino devuelve null
    public static User getLoginUser(HttpServletRequest req) {
        return (User) req.getSession().getAttribute("user");
    }
}

