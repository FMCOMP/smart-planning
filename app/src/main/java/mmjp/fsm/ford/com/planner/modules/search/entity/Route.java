package mmjp.fsm.ford.com.planner.modules.search.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import mmjp.fsm.ford.com.utils.JPUtils;


public class Route implements Serializable{

    @SerializedName("routeMode")
    public String routeMode;

    @SerializedName("fare")
    private Double routeFare;

    @SerializedName("leaveTime")
    private Long routeDeparture;

    @SerializedName("arriveTime")
    private Long routeArrival;

    @SerializedName("duration")
    private Integer routeDuration;

    @SerializedName("avgWalkDistance")
    public RouteDistance routeAvgWalkDistance;

    @SerializedName("totalWalkDistance")
    private RouteDistance routeWalkDistance;

    @SerializedName("totalBusTime")
    private Integer routeBusDuration;

    @SerializedName("mobilityProvider")
    private RouteMobility routeMobility;

    @SerializedName("segments")
    private ArrayList<RouteSegment> routeSegments;

    @SerializedName("totalNoOfConnections")
    private int routeConnections;

    @SerializedName("totalNoOfSegments")
    private int routeSegmentCount;

    @SerializedName("globalFare")
    private RouteFare globalFare;


    public static Route newInstance(){
        return new Route();
    }

    public String getRouteMode() {
        return routeMode;
    }

    public Long getRouteArrival() {
        return routeArrival;
    }

    public String getRouteFare(){
        return Double.toString(routeFare);
    }

    public String getRouteDeparture() {
        return JPUtils.convertEpochTime(routeDeparture);
    }

    public String getRouteDuration(){
        return Integer.toString(routeDuration);
    }

    public RouteDistance getRouteWalkDistance(){
        return routeWalkDistance;
    }

    public String getRouteArrivalTime(){
        return JPUtils.convertEpochTime(routeArrival);
    }

    public int getRouteConnections(){
        return routeConnections;
    }

    public int getRouteSegmentCount(){
        return routeSegmentCount;
    }

    public String getRouteBusDuration(){
        return Integer.toString(routeBusDuration);
    }

    public RouteDistance getRouteAvgWalkDistance(){
        return routeAvgWalkDistance;
    }

    public RouteMobility getRouteMobility(){
        return routeMobility;
    }

    public RouteFare getGlobalFare() {
        return globalFare;
    }

    public ArrayList<RouteSegment> getRouteSegments(){
        return routeSegments;
    }

    public void setGlobalFare(RouteFare globalFare) {
        this.globalFare = globalFare;
    }

    public void setRouteArrival(Long routeArrival) {
        this.routeArrival = routeArrival;
    }

    public void setRouteAvgWalkDistance(RouteDistance routeAvgWalkDistance) {
        this.routeAvgWalkDistance = routeAvgWalkDistance;
    }

    public void setRouteBusDuration(Integer routeBusDuration) {
        this.routeBusDuration = routeBusDuration;
    }

    public void setRouteConnections(int routeConnections) {
        this.routeConnections = routeConnections;
    }

    public void setRouteDeparture(Long routeDeparture) {
        this.routeDeparture = routeDeparture;
    }

    public void setRouteDuration(Integer routeDuration) {
        this.routeDuration = routeDuration;
    }

    public void setRouteFare(Double routeFare) {
        this.routeFare = routeFare;
    }

    public void setRouteMobility(RouteMobility routeMobility) {
        this.routeMobility = routeMobility;
    }

    public void setRouteMode(String routeMode) {
        this.routeMode = routeMode;
    }

    public void setRouteSegmentCount(int routeSegmentCount) {
        this.routeSegmentCount = routeSegmentCount;
    }

    public void setRouteSegments(ArrayList<RouteSegment> routeSegments) {
        this.routeSegments = routeSegments;
    }

    public void setRouteWalkDistance(RouteDistance routeWalkDistance) {
        this.routeWalkDistance = routeWalkDistance;
    }
}