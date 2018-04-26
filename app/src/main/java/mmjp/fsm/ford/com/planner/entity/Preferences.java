package mmjp.fsm.ford.com.planner.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by troger56 on 3/18/18.
 */

public class Preferences implements Serializable {
    @SerializedName("maxNoOfConnections")
    private Integer maxNoOfConnections;

    @SerializedName("maxWalkDistance")
    private Integer maxWalkDistance;


    public static Preferences newInstance(){
        return new Preferences();
    }

    private Preferences(){
        maxNoOfConnections = 5;
        maxWalkDistance = 5000;
    }

    public void setMaxWalkDistance(int distance){
        maxWalkDistance = distance;
    }
    public int getMaxWalkDistance(){
        return  maxWalkDistance;
    }

    public void setMaxNoOfConnections(int connections){
        maxNoOfConnections = connections;
    }
    public int getMaxNoOfConnections(){
        return maxNoOfConnections;
    }
}
