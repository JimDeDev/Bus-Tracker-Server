package bustracker.models;

import org.json.JSONObject;

public class Route {

    private String id;
    private String shortName;
    private String longName;
    private int type;

    public Route(String id, String shortName, String longName, int type) {
        this.id = id;
        this.shortName = shortName;
        this.longName = longName;
        this.type = type;
    }

    public Route() {
        this.id = null;
        this.shortName = null;
        this.longName = null;
        this.type = -1;
    }

    /**
     * Constructs a Route object from a json object provided by the AT API
     * 
     * @param jsonRoute
     */
    public Route(JSONObject jsonRoute) {
        this.setId(jsonRoute.getString("route_id"));
        this.setShortName(jsonRoute.getString("route_short_name"));
        this.setLongName(jsonRoute.getString("route_long_name"));
        this.setType(jsonRoute.getInt("route_type"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Route:\n" + "\nid: " + id + "\nshortName: " + shortName + "\nlongName: " + longName + "\ntype: " + type;
    }

}
