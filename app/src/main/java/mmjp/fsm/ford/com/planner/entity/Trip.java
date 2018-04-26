package mmjp.fsm.ford.com.planner.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import mmjp.fsm.ford.com.utils.AppUtils;

/**
 * Created by troger56 on 3/21/18.
 */

public class Trip implements Serializable {
    @SerializedName("origin")
    public Location tripOrigin;

    @SerializedName("destination")
    public Location tripDestination;

    @SerializedName("tripOptionFilter")
    public Preferences tripPreferences;

    @SerializedName("dateTime")
    public String tripDeparture;

    @SerializedName("address")
    public String userAddress;

    public static Trip newInstance(){
        return new Trip();
    }

    public Trip(){}

    public void setUserAddress(String address){
        userAddress = address;
    }

    public long getTripDepartureTime(){
        return new Date().getTime();
    }
    public void setTripDepartureTime(long departure){
        tripDeparture = AppUtils.DATE_TIME_FORMAT.format(departure);
    }

    public Location getTripOrigin(){
        return tripOrigin;
    }
    public void setTripOrigin(Location origin){
        tripOrigin = origin;
    }

    public Location getTripDestination(){
        return tripDestination;
    }
    public void setTripDestination(Location destination){
        tripDestination = destination;
    }

    public Preferences getTripPreferences(){
        return tripPreferences;
    }
    public void setTripPreferences(Preferences preferences){
        tripPreferences = preferences;
    }

}
