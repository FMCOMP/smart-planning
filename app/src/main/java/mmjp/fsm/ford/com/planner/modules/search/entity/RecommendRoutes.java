package mmjp.fsm.ford.com.planner.modules.search.entity;

import com.google.gson.annotations.SerializedName;

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
