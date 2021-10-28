package biblioteca.model;

import biblioteca.dao.DB;

import java.time.LocalDate;

public class Trip {

    private int tripId;
    private int driverDni;
    private int truckPlate;
    private Location from;
    private Location to;
    private LocalDate start;
    private LocalDate end;

    public Trip(int tripId) {
        this.tripId = tripId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getDriverDni() {
        return driverDni;
    }

    public void setDriverDni(int driverDni) {
        this.driverDni = driverDni;
    }

    public int getTruckPlate() {
        return truckPlate;
    }

    public void setTruckPlate(int truckPlate) {
        this.truckPlate = truckPlate;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public void setFrom(String from) {
        this.from = Location.valueOf(from);
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public void setTo(String to) {
        this.to = Location.valueOf(to);
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }


    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public int getDistance() {
        return from.getDistanceTo(to);
    }

    //la cantidad de dias q tarde un viaje en completarse
    public double getTimeDays() {
        return getDistance() / 200.0;
    }

    //la cantidad de tanques q se nesecitan para hacer le recorrido
    public double getTanks() {
        final Truck truck = DB.getInstance().getTruckDAO().find(getTruckPlate());
        if (truck == null) return 0;

        final int distance = getDistance(); // 100km
        final float consumption = truck.getConsumption(); // 10 l/km
        final int tankCapacity = truck.getTankCapacity();//50l
        //100*10 = 1000
        final double litrosRequeridos = distance * consumption;
        return litrosRequeridos / tankCapacity; // 1000/50 = 20
    }
}
