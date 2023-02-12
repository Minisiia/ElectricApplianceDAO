package electric_appliance.dao;

import electric_appliance.entity.Appliance;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApplianceDAO implements IApplianceDAO{
    @Override
    public void add(Appliance appliance) {
        Connection connection = null;
        connection = getConnection();
        PreparedStatement ps = null;
        String str = "INSERT INTO house_appliances (name,power,isPowered) VALUES (?,?,?);";
        try {
            ps = connection.prepareStatement(str);
            ps.setString(1,appliance.getName());
            ps.setInt(2,appliance.getPower());
            ps.setBoolean(3,appliance.isPowered());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null && ps != null) {
                try {
                    connection.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void turnOn(String name) {
        Connection connection = null;
        connection = getConnection();
        PreparedStatement ps = null;
        String str = "UPDATE house_appliances SET isPowered = true WHERE name = ?;";
        try {
            ps = connection.prepareStatement(str);
            ps.setString(1,name);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null && ps != null) {
                try {
                    connection.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void turnOff(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        String str = "UPDATE house_appliances SET isPowered = false WHERE name = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,name);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null && ps != null) {
                try {
                    connection.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Appliance> getAll() {
        List<Appliance> al = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        String str = "SELECT * FROM house_appliances;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int power = rs.getInt(3);
                boolean isPowered = rs.getBoolean(4);
                Appliance appliance = new Appliance(id, name, power, isPowered);
                al.add(appliance);
            }
            return al;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null && ps != null) {
                try {
                    connection.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Appliance> sortByPower() {
        List<Appliance> al = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        String str = "SELECT * FROM house_appliances ORDER BY power;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int power = rs.getInt(3);
                boolean isPowered = rs.getBoolean(4);
                Appliance appliance = new Appliance(id, name, power, isPowered);
                al.add(appliance);
            }
            return al;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null && ps != null) {
                try {
                    connection.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Appliance> getAppliance(int minPower, int maxPower) {
        List<Appliance> al = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        String str = "SELECT * FROM house_appliances WHERE power>? AND power<?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,minPower);
            ps.setInt(2, maxPower);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int power = rs.getInt(3);
                boolean isPowered = rs.getBoolean(4);
                Appliance appliance = new Appliance(id, name, power, isPowered);
                al.add(appliance);
            }
            return al;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null && ps != null) {
                try {
                    connection.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int sumPower() {
        Connection connection = null;
        PreparedStatement ps = null;
        String str = "SELECT SUM(power) FROM house_appliances WHERE isPowered=true;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null && ps != null) {
                try {
                    connection.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    private Connection getConnection() {//возвращает соединение
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricappliance", "root", "root");
            return connection;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
