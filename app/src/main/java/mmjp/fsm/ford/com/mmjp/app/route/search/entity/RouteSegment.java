package mmjp.fsm.ford.com.mmjp.app.trip.modules.route.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import mmjp.fsm.ford.com.mmjp.app.trip.entity.Location;
import mmjp.fsm.ford.com.mmjp.utils.JPUtils;

public class RouteSegment implements Serializable {

    @SerializedName("mode")
    private String segmentMode;

    @SerializedName("startAddress")
    private Location segmentStartAddress;

    @SerializedName("distance")
    private RouteDistance segmentDistance;

    @SerializedName("endAddress")
    private Location segmentEndAddress;

    @SerializedName("startTime")
    private Long segmentStartTime;

    @SerializedName("endTime")
    private Long segmentEndTime;

    @SerializedName("duration")
    private Integer segmentDuration;

    @SerializedName("scheduleLink")
    private String segmentScheduleLink;

    @SerializedName("routeName")
    private String segmentRouteName;

    @SerializedName("agency")
    private String segmentAgency;

    @SerializedName("numberOfStops")
    private String segmentStops;

    public String getSegmentMode(){
        return segmentMode;
    }

    public String getSegmentDuration() {
        return Integer.toString(segmentDuration);
    }

    public Location getSegmentEndAddress() {
        return segmentEndAddress;
    }

    public Location getSegmentStartAddress() {
        return segmentStartAddress;
    }

    public String getSegmentEndTime() {
        return JPUtils.convertEpochTime(segmentEndTime);
    }

    public String getSegmentStartTime() {
        return JPUtils.convertEpochTime(segmentStartTime);
    }

    public String getSegmentAgency() {
        return segmentAgency;
    }

    public  RouteDistance getSegmentDistance() {
        return segmentDistance;
    }

    public String getSegmentRouteName() {
        return segmentRouteName;
    }

    public String getSegmentScheduleLink() {
        return segmentScheduleLink;
    }

    public String getSegmentStops() {
        return segmentStops;
    }
}