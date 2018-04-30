package mmjp.fsm.ford.com.planner.modules.search.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import mmjp.fsm.ford.com.planner.entity.Location;

/**
 * Created by troger56 on 3/22/18.
 */

public class RouteOptions extends Route {
    @SerializedName("origin")
    public Location origin;

    @SerializedName("destination")
    public Location destination;

    @SerializedName("price")
    public ArrayList<Route> priceRouteOptions;

    @SerializedName("time")
    public ArrayList<Route> timeRouteOptions;

    @SerializedName("connections")
    public ArrayList<Route> connectionsRouteOptions;

    public ArrayList<Route> getPriceRouteOptions() {
        return priceRouteOptions;
    }

    public ArrayList<Route> getConnectionsRouteOptions() {
        return connectionsRouteOptions;
    }

    public ArrayList<Route> getTimeRouteOptions() {
        return timeRouteOptions;
    }

}
