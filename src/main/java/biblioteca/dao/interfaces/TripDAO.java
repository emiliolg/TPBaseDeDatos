package biblioteca.dao.interfaces;

import biblioteca.model.Trip;

import java.util.List;

public interface TripDAO extends CRUD<Trip, Integer> {
    List<Trip> listDriverTrips(int driverDNI);
}
