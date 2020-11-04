package bustracker;

import org.json.JSONTokener;

public interface IApiHandler {

    public abstract String getStops();

	public abstract String getRoutes();

}