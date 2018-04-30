package mmjp.fsm.ford.com.planner.modules.search.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RouteFare implements Serializable {
    @SerializedName("fare")
    private float transitFare;

    @SerializedName("units")
    private String fareUnitMeasurement;

    public String getFare() {
        return Double.toString(transitFare);
    }

    public String getTransitUnitMeasurement() {
        return fareUnitMeasurement;
    }
}
