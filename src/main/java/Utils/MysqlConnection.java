package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    private static Connection con;

    private static void connect(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factures?characterEncoding=latin1&useConfigs=maxPerformance","root","root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static Connection getConnection(){
        if(con == null) connect();
        return con;
    }

    public static void main(String[] args){
        System.out.println(getConnection().toString());
    }
}
