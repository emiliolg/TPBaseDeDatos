package biblioteca.servlet.admin;

import biblioteca.dao.DB;
import biblioteca.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet({"empleados"})
public class EmpleadoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final List<Empleado> empleados = DB.getInstance().getEmpleadoDAO().list(); // busca la lista

        req.setAttribute("emplea" +
                "dos", empleados);

        req.getRequestDispatcher("/admin/empleados.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //si el ID es distinto de nulo, llama al doDelete
        if (req.getParameter("id")!= null) {
            doDelete(req, resp);
            return;
        }

        // agarramos los datos posteados
        int dni = Integer.parseInt(req.getParameter("dni"));
        String firstName = req.getParameter("nombre");
        String lastName = req.getParameter("apellido");
        LocalDate birthDay = LocalDate.parse(req.getParameter("fecha_nacimiento"));
        String turno = req.getParameter("turno");

        // creamos el nuevo empleado
        Empleado driver = new Empleado(dni);
        driver.setNombre(firstName);
        driver.setApellido(lastName);
        driver.setFechaNacimiento(birthDay);
        driver.setTurno(turno);

        DB.getInstance().getEmpleadoDAO().insert(driver);

        // volvemos a cargar el listado de camiones
        req.setAttribute("msg", "Se guardo el nuevo chofer!");
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // borro el chofer con ese id
        String id = req.getParameter("id");
        DB.getInstance().getEmpleadoDAO().delete(Integer.parseInt(id));

        // volvemos a cargar el listado de camiones
        req.setAttribute("msg", "Se borro el chofer!");
        doGet(req, resp);
    }
}
