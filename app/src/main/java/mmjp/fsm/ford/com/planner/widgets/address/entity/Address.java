package mmjp.fsm.ford.com.planner.widgets.address.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by troger56 on 3/21/18.
 */

public class Address implements Serializable {

    @SerializedName("country")
    public String country;

    @SerializedName("state")
    public String state;

    @SerializedName("county")
    public String county;

    @SerializedName("city")
    public String city;

    @SerializedName("street")
    public String street;

    @SerializedName("houseNumber")
    public String houseNumber;

    @SerializedName("postalCode")
    public String postalCode;

    public String getCity() {
        return (street != null ) ? city+", "+state+", "+country+", "+postalCode
                : (city != null) ? state+", "+country+", "+postalCode
                : (state != null) ? country+", "+postalCode : country+", "+postalCode;
    }

    public String getFullAddress() {
        return (houseNumber != null) ? houseNumber+" "+street+", "+city+", "+state+", "+postalCode
                : (street != null) ? street+", "+city+", "+state+", "+postalCode
                : city+", "+state+", "+postalCode ;
    }
}
