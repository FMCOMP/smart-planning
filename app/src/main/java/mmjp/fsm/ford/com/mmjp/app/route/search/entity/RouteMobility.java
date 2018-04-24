package mmjp.fsm.ford.com.mmjp.app.trip.modules.route.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by troger56 on 3/18/18.
 */

public class RouteMobility {
    @SerializedName("id")
    public String id;

    @SerializedName("type")
    public String type;

    @SerializedName("name")
    public String name;

    @SerializedName("hasTransfer")
    public Boolean hasTransfer;

    public Boolean getHasTransfer() {
        return hasTransfer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
