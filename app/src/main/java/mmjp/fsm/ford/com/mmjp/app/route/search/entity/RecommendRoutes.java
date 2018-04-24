package mmjp.fsm.ford.com.mmjp.app.trip.modules.route.entity;

import com.google.gson.annotations.SerializedName;

import mmjp.fsm.ford.com.mmjp.app.trip.modules.route.entity.Route;

/**
 * Created by troger56 on 3/22/18.
 */

public class RecommendRoutes extends Route {
    @SerializedName("label")
    public String label;

    @SerializedName("tripOption")
    public Route routingOptions;

    public String getLabel() {
        return label;
    }

    public Route getRoutingOptions() {
        return routingOptions;
    }


}
