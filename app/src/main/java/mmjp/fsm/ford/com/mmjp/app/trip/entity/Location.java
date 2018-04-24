package mmjp.fsm.ford.com.mmjp.app.trip.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by troger56 on 3/18/18.
 */

public class Location implements Serializable {

    @SerializedName("location")
     public String userLocation;

    @SerializedName("latitude")
     public Double userLatitude;

    @SerializedName("longitude")
     public Double userLongitude;


    public Location(){}

    public void setUserCoordinates(Double latitude, Double longitude){
        setUserLatitude(latitude);
        setUserLongitude(longitude);
    }

    public String getUserLocation(){
        return userLocation;
    }
    public void setUserLocation(String location){
        userLocation = location;
    }

    public String getUserLatitude(){
        return Double.toString(userLatitude);
    }
    private void setUserLatitude(Double latitude){
        userLatitude = latitude;
    }

    public String getUserLongitude(){
        return Double.toString(userLongitude);
    }
    private void setUserLongitude(Double longitude){
        userLongitude = longitude;
    }

}
