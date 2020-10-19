package bustracker;

import java.util.ArrayList;
import bustracker.models.*;

public class ModelManager {

    private ArrayList<Stop> stops;
    private ArrayList<Route> routes;
    private DatabaseManager dbManager;
    private IApiHandler apiHandler;

    public ModelManager() {

        this.stops = new ArrayList<Stop>();
        this.routes= new ArrayList<Route>();
        this.dbManager = new DatabaseManager();
        this.apiHandler = new ApiHandler();

    }

}