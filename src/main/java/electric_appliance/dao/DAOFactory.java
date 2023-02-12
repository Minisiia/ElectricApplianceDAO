package electric_appliance.dao;

public class DAOFactory implements IDAOFactory {
    private static IDAOFactory factory;

    private DAOFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static synchronized IDAOFactory getInstance() {//синхронайзд используется что бы два пользователя не могли одновременно работать
        if (factory == null) {//при первом обращении тру
            factory = new DAOFactory();//фабрика создасться один раз
        }//при повторном обращении будет фолс
        return factory;
    }
    @Override
    public ApplianceDAO getApplianceDAO() {
        return new ApplianceDAO();
    }
}
