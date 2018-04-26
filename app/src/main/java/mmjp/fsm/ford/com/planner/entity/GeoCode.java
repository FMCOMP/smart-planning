package mmjp.fsm.ford.com.planner.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeoCode implements Serializable {

    @SerializedName("locationId")
    private String locationId;

    @SerializedName("locationLat")
    private Double latitude;

    @SerializedName("locationLng")
    private Double longitude;

    public String getLocationId(){
        return locationId;
    }
    public Double getLongitude(){
        return longitude;
    }
    public Double getLatitude(){
        return latitude;
    }
}