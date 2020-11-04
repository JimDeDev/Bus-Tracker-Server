package bustracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiHandler implements IApiHandler {

    private static String primaryKey = "";
    private static String secondaryKey = "";

    public ApiHandler(String primary, String secondary) {
        primaryKey = primary;
        secondaryKey = secondary;
    }

    @Override
    public String getStops() {
        System.out.print("Getting stop data from AT server ... ");
        String result = "";

        try {
            URL url = new URL("https://api.at.govt.nz/v2/gtfs/stops");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Ocp-Apim-Subscription-Key", primaryKey);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            result = br.readLine();

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("SUCCESS");
        return result;
    }

    @Override
    public String getRoutes() {
        System.out.print("Getting route data from AT server ... ");
        String result = "";

        try {
            URL url = new URL("https://api.at.govt.nz/v2/gtfs/routes");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Ocp-Apim-Subscription-Key", primaryKey);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            result = br.readLine();

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("SUCCESS");
        return result;
    }

}