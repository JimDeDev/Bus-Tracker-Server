package bustracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiHandler implements IApiHandler {

    // TODO: Hide this
    private static String accessToken = "";

    public ApiHandler() {
        System.out.println("Creating API Handler...");
    }

    @Override
    public String getStops() {
        System.out.println("Getting bus stop data...");
        String result = "";

        try {
            URL url = new URL("https://api.at.govt.nz/v2/gtfs/stops");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Ocp-Apim-Subscription-Key", this.accessToken);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            // while ((result = br.readLine()) != null) {
            //     System.out.println(result);
            // }

            result = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("retrieved stop data");
        return result;
    }
    
}