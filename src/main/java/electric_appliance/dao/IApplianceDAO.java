package electric_appliance.dao;

import electric_appliance.entity.Appliance;

import java.util.List;

public interface IApplianceDAO {
    void add(Appliance appliance);
    void turnOn(String name);
    void turnOff(String name);
    List<Appliance> getAll();
    List<Appliance> sortByPower();
    List<Appliance> getAppliance (int minPower, int maxPower);
    int sumPower();
}
