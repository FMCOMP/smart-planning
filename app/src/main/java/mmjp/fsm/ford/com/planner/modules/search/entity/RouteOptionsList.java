package mmjp.fsm.ford.com.planner.modules.search.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import mmjp.fsm.ford.com.planner.entity.Location;

public class RouteOptionsList implements Serializable {
    @SerializedName("type")
    public Location tripOrigin;

    @SerializedName("leaveTime")
    public Location tripDestination;

    @SerializedName("recommendedOptions")
    public Long routeArrival;

    @SerializedName("duration")
    public Integer routeDuration;

    @SerializedName("avgWalkDistance")
    public Integer routeWalkTime;

    @SerializedName("totalWalkDistance")
    public Integer routeWalkDistance;

    @SerializedName("mobilityProvider")
    public RouteMobility routeMobility;

    @SerializedName("segments")
    public List<RouteSegment> routeSegments;
}
