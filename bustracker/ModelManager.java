package bustracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import bustracker.models.*;
import org.json.*;

public class ModelManager {

    private static String AT_API_KEY_PRIMARY;
    private static String AT_API_KEY_SECONDARY;
    private static String DB_USER;
    private static String DB_PASS;

    private ArrayList<Stop> stops;
    private ArrayList<Route> routes;
    private DatabaseManager dbManager;
    private IApiHandler apiHandler;

    public ModelManager() {

        this.loadSecrets();

        this.stops = new ArrayList<Stop>();
        this.routes = new ArrayList<Route>();
        this.dbManager = new DatabaseManager(DB_USER, DB_PASS);
        this.apiHandler = new ApiHandler(AT_API_KEY_PRIMARY, AT_API_KEY_SECONDARY);

        this.loadModels();
    }

    /**
     * This method loads the API keys and database credentials into memory
     */
    private void loadSecrets() {
        System.out.print("Loading secrets from disk ... ");
        String secretsRawString = "";
        try {

            Scanner scanner = new Scanner(new File("secrets.json"));

            while (scanner.hasNextLine()) {
                secretsRawString += scanner.nextLine();
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("FAIL");
            System.err.println("The secrets.json file was not found!");
            System.err.println("See the readme for further information.");
            System.exit(1);
        }

        JSONObject secrets = new JSONObject(secretsRawString);

        JSONObject atKey = secrets.getJSONObject("AT-API-Key");
        AT_API_KEY_PRIMARY = atKey.getString("primary");
        AT_API_KEY_SECONDARY = atKey.getString("secondary");

        JSONObject dbCreds = secrets.getJSONObject("MySql-Login");
        DB_USER = dbCreds.getString("username");
        DB_PASS = dbCreds.getString("password");
        System.out.println("SUCCESS");
    }

    private void loadModels() {
        this.loadRoutes();
        this.loadStops();
    }

    /**
     * This method will call the AT route API,
     * Build Route objects with the returned data,
     * and save the data to both the database and to 
     * the model manager.
     */
    private void loadRoutes() {
        JSONObject routeData = new JSONObject(this.apiHandler.getRoutes());

        JSONArray routes = routeData.getJSONArray("response");

        for (int i = 0; i < routes.length(); i++) {
            JSONObject jsonRoute = routes.getJSONObject(i);
            Route route = new Route(jsonRoute);

            dbManager.saveRoute(route);
            this.routes.add(route);
        }

        System.out.println("There are " + routes.length() + " routes");
    }

    /**
     * This method will call the AT stop API,
     * Build Stop objects with the returned data,
     * and save the data to both the database and to 
     * the model manager.
     */
    private void loadStops() {
        JSONObject stopData = new JSONObject(this.apiHandler.getStops());

        JSONArray stops = stopData.getJSONArray("response");

        for (int i = 0; i < stops.length(); i++) {
            JSONObject jsonStop = stops.getJSONObject(i);
            Stop stop = new Stop(jsonStop);

            dbManager.saveStop(stop);
            this.stops.add(stop);
        }

        System.out.println("There are " + stops.length() + " stops");
    }

    public void close() {
        this.dbManager.close();
    }
}