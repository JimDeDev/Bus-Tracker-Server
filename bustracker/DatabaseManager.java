package bustracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bustracker.models.Stop;

public class DatabaseManager {

    private static final String url = "jdbc:mysql://localhost:3306/tracker_schema";
    private static String username = "";
    private static String password = "";

    private Connection conn;
    private Statement stmt;

    public DatabaseManager() {
        
        this.connect();
    }
    
    private void connect() {
        System.out.println("Connecting database...");
        try {
            this.conn = DriverManager.getConnection(url, username, password);
            this.stmt = this.conn.createStatement();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        System.out.println("Database connection established.");
    }
    
    public void close() {
        try {
            this.stmt.close();
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveStop(Stop s) {

        String query = "SELECT * FROM transitStop WHERE stopId = '" + s.getId() + "';";

        ResultSet rs;
        try {
            rs = this.stmt.executeQuery(query);
            if (rs.next()) {
                return;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // This if statement is to format the stop names for the database. Db requires double apostrophizes.
        String stopName = s.getName();
        if (s.getName().contains("'")) { 
             stopName = stopName.replace("'", "''");
        }

        String statement = "INSERT INTO transitStop VALUES (";
        statement += "'" + s.getId() + "',";
        statement += s.getCode() + ",";
        statement += "'" + stopName + "',";
        statement += s.getLat() + ",";
        statement += s.getLng() + ",";
        statement += "'" + s.getGeom() + "');";

        try {
            this.stmt.executeUpdate(statement);
        } catch (SQLException e) {
            System.out.println(statement);
            e.printStackTrace();
        }
    }
}
