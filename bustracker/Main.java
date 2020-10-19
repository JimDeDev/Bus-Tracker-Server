package bustracker;

import org.json.*;

import bustracker.models.Stop;

public class Main {
	public static void main(String[] args) {

		DatabaseManager dbManager = new DatabaseManager();

		IApiHandler api = new ApiHandler();


		JSONObject stopData = new JSONObject( api.getStops());

		JSONArray stops = stopData.getJSONArray("response");

		int stopNameLength = 0;


		for (int i = 0; i < stops.length(); i++) {
			JSONObject jsonStop = stops.getJSONObject(i);
			Stop stop = new Stop();
			
			stop.setId(jsonStop.getString("stop_id"));
			stop.setCode(jsonStop.getInt("stop_code"));
			stop.setName(jsonStop.getString("stop_name"));
			stop.setLat(jsonStop.getFloat("stop_lat"));
			stop.setLng(jsonStop.getFloat("stop_lon"));
			stop.setGeom(jsonStop.getString("the_geom"));

			dbManager.saveStop(stop);

		}

		System.out.println(stopNameLength);
		System.out.println("There are " + stops.length() + " stops");


		dbManager.close();
	}

}






				// try {
				// 	FileWriter fw = new FileWriter(new File("JSON.txt"));
		
				// 	fw.append(stopData);
				// 	fw.close();
				// } catch (IOException e) {
				// 	// TODO Auto-generated catch block
				// 	e.printStackTrace();
				// }