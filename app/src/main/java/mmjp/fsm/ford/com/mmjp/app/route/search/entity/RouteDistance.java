package mmjp.fsm.ford.com.mmjp.app.trip.modules.route.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by troger56 on 3/29/18.
 */

public class RouteDistance implements Serializable {
    @SerializedName("distance")
    private Double transitDistance;

    @SerializedName("units")
    private String transitUnitMeasurement;

    public String getTransitDistance() {
        return Double.toString(transitDistance);
    }

    public String getTransitUnitMeasurement() {
        return transitUnitMeasurement;
    }
}
