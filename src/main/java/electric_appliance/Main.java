package electric_appliance;

import electric_appliance.dao.ApplianceDAO;
import electric_appliance.dao.DAOFactory;
import electric_appliance.dao.IDAOFactory;
import electric_appliance.entity.Appliance;

import java.util.List;

/**
 * Домашние электроприборы. Определить иерархию электроприборов. Включить некоторые в розетку. Подсчитать потребляемую мощность.
 * Провести сортировку приборов в квартире на основе мощности. Найти прибор в квартире, соответствующий заданному диапазону параметров.
 * Используйте доступ к базе данных и DAO.
 * <p>
 * Household electrical appliances. Determine the hierarchy of electrical appliances. Plug some in. Calculate the power consumption.
 * Carry out sorting of appliances in the apartment based on power. Find a device in the apartment that matches the specified range of parameters.
 * Use database access and DAO.
 */

public class Main {
    public static void main(String[] args) {
        IDAOFactory idaoFactory = DAOFactory.getInstance();
        ApplianceDAO applianceDAO = idaoFactory.getApplianceDAO();
        Appliance computer = new Appliance("computer", 250);
        Appliance dishWasher = new Appliance("dishWasher", 2500);
        Appliance kettle = new Appliance("kettle", 2000);
        Appliance stove = new Appliance("stove", 7000);
        Appliance fridge = new Appliance("fridge", 300);
        Appliance iron = new Appliance("iron", 2000);
        Appliance tv = new Appliance("tv", 150);
        Appliance washer = new Appliance("washer", 1500);

        applianceDAO.add(computer);
        applianceDAO.add(dishWasher);
        applianceDAO.add(kettle);
        applianceDAO.add(stove);
        applianceDAO.add(fridge);
        applianceDAO.add(iron);
        applianceDAO.add(tv);
        applianceDAO.add(washer);

        System.out.println((char) 27 + "[34m" + "All electric appliances in the house:" + (char) 27 + "[38m");
        List<Appliance> list = applianceDAO.getAll();
        for (Appliance temp : list) System.out.println(temp);

        System.out.println((char) 27 + "[34m" + "\nTurn on some appliances:" + (char) 27 + "[38m");
        applianceDAO.turnOn("computer");
        applianceDAO.turnOn("stove");
        applianceDAO.turnOn("iron");
        applianceDAO.turnOn("fridge");
        applianceDAO.turnOff("fridge");
        list = applianceDAO.getAll();
        for (Appliance temp : list) System.out.println(temp);

        System.out.println((char) 27 + "[34m" + "\nPower consumption: " + (char) 27 + "[38m" + applianceDAO.sumPower());

        System.out.println((char) 27 + "[34m" + "\nSorted by power: " + (char) 27 + "[38m");
        list = applianceDAO.sortByPower();
        for (Appliance temp : list) System.out.println(temp);

        System.out.println((char) 27 + "[34m" + "\nElectrical appliances with a power of less than 2000 and more than 200 watts: " + (char) 27 + "[38m");
        list = applianceDAO.getAppliance(200, 2000);
        for (Appliance temp : list) System.out.println(temp);
    }
}
