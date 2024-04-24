package backend;

import java.sql.*;

public class weatherDB {
    public static void main(String[] args){
        try{
            System.out.println("Connecting...");
            Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.2:3306/weatherinfo?zeroDateTimeBehavior=CONVERT_TO_NULL [root on Default schema]",
                "root",
                "meetmeinthedark"
            );
        
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM weather");

            while(resultSet.next()){
                System.out.println(resultSet.getInt("no"));
                System.out.println(resultSet.getString("country"));
                System.out.println(resultSet.getInt("currenttmp"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
