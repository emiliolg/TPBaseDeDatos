package biblioteca.model;

public class Truck {

    private final int plateNumber;
    private String brand;
    private String model;
    private int maxTons;
    private int tankCapacity;
    private int consumption;

    public Truck(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxTons() {
        return maxTons;
    }

    public void setMaxTons(int maxTons) {
        this.maxTons = maxTons;
    }

    public int getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
}
