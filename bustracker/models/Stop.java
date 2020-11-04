package bustracker.models;

import org.json.JSONObject;

public class Stop {

    private String id;
    private int code;
    private String name;
    private float lat;
    private float lng;
    private String geom;

    public Stop(String id, int code, String name, float lat, float lng, String geom) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.geom = geom;
    }

    public Stop() {
        this.id = null;
        this.code = -1;
        this.name = null;
        this.lat = -1.0f;
        this.lng = -1.0f;
        this.geom = null;
    }

    /**
     * Constructs a Stop object from a json object provided by the AT API
     * 
     * @param jsonStop
     */
    public Stop(JSONObject jsonStop) {
        this.setId(jsonStop.getString("stop_id"));
        this.setCode(jsonStop.getInt("stop_code"));
        this.setName(jsonStop.getString("stop_name"));
        this.setLat(jsonStop.getFloat("stop_lat"));
        this.setLng(jsonStop.getFloat("stop_lon"));
        this.setGeom(jsonStop.getString("the_geom"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    @Override
    public String toString() {
        return "Stop:\n" + "\nid: " + id + "\ncode: " + code + "\nname: " + name + "\nlat: " + lat + "\nlng: " + lng
                + "\ngeom: " + geom;
    }
}
