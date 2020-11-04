package bustracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bustracker.models.Route;
import bustracker.models.Stop;

public class DatabaseManager {

    private static final String url = "jdbc:mysql://localhost:3306/tracker_schema";
    private static String username = "";
    private static String password = "";

    private Connection conn;
    private Statement stmt;

    public DatabaseManager(String user, String pass) {

        username = user;
        password = pass;

        this.connect();
    }

    private void connect() {
        System.out.print("Connecting to database ... ");
        try {
            this.conn = DriverManager.getConnection(url, username, password);
            this.stmt = this.conn.createStatement();
        } catch (SQLException e) {
            System.out.println("FAIL");
            System.err.println("Database Connection Failed.");
            System.exit(1);
        }
        System.out.println("SUCCESS");
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

        if (this.entityExists("transitStop", s.getId())) {
            return;
        }

        // This if statement is to format the stop names for the database. Db requires
        // double apostrophizes.
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

    public void saveRoute(Route r) {
        if (this.entityExists("route", r.getId())) {
            return;
        }

        String statement = "INSERT INTO route VALUES (";
        statement += "'" + r.getId() + "',";
        statement += "'" + r.getShortName() + "',";
        statement += "'" + r.getLongName() + "',";
        statement += r.getType() + ")";

        try {
            this.stmt.executeUpdate(statement);
        } catch (SQLException e) {
            System.out.println(statement);
            e.printStackTrace();
        }
    }

    /**
     * Queries the database and returns true if an entity in the given table has the
     * given id.
     * 
     * @param table
     * @param id
     * @return true if matching entity found.
     */
    public boolean entityExists(String table, String id) {

        String query = "SELECT * FROM " + table + " WHERE ID = '" + id + "';";

        try {
            ResultSet rs = this.stmt.executeQuery(query);
            if (rs.next()) {
                rs.close();
                return true;
            }
            rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return false;
    }
}
