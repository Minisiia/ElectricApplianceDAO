package electric_appliance.entity;

public class Appliance {
    private int id;
    private String name;
    private int power;
    private boolean isPowered;

    public Appliance() {
    }

    public Appliance(String name, int power) {
        id = 0;
        this.name = name;
        this.power = power;
        isPowered = false;
    }

    public Appliance(String name, int power, boolean isPowered) {
        id = 0;
        this.name = name;
        this.power = power;
        this.isPowered = isPowered;
    }

    public Appliance(int id, String name, int power, boolean isPowered) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.isPowered = isPowered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", power=" + power +
                ", isPowered=" + isPowered;
    }
}
