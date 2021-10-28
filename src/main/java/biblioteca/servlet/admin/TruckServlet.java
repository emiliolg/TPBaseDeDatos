package biblioteca.servlet.admin;

import biblioteca.dao.DB;
import biblioteca.model.Truck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"trucks"}) //El servlet para la lista de camiones
public class TruckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final List<Truck> trucks = DB.getInstance().getTruckDAO().list(); // busca la lista

        req.setAttribute("trucks", trucks);

        req.getRequestDispatcher("/admin/trucks.jsp").include(req, resp);
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
        int plateNumber = Integer.parseInt(req.getParameter("plate_number"));
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        int maxTons = Integer.parseInt(req.getParameter("max_tons"));
        int tankCapacity = Integer.parseInt(req.getParameter("tank_capacity"));
        int consumption = Integer.parseInt(req.getParameter("consuption"));

        // creamos el nuevo camion
        Truck truck = new Truck(plateNumber);
        truck.setBrand(brand);
        truck.setModel(model);
        truck.setMaxTons(maxTons);
        truck.setTankCapacity(tankCapacity);
        truck.setConsumption(consumption);

        // guardamos el nuevo camion
        DB.getInstance().getTruckDAO().insert(truck);

        // volvemos a cargar el listado de camiones
        req.setAttribute("msg", "Se guardo el nuevo camion!");
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // borro el camion con ese id
        String id = req.getParameter("id");
        DB.getInstance().getTruckDAO().delete(Integer.parseInt(id));

        // volvemos a cargar el listado de camiones
        req.setAttribute("msg", "Se borro el camion!");
        doGet(req, resp);
    }
}

