package mmjp.fsm.ford.com.mmjp.app.widgets.address.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by troger56 on 3/21/18.
 */

public class AddressSuggestion implements Serializable {

    @SerializedName("label")
    public String label;

    @SerializedName("address")
    public Address address;

    @SerializedName("locationId")
    public String locationId;

    public static AddressSuggestion newInstance(){
        return new AddressSuggestion();
    }

    public String getCity() {
        return address.city + ", " + address.state + ", "+ address.postalCode;
    }

    public String getLabel() {
        return (label != null && !label.isEmpty()) ? label
                : (address.houseNumber != null) ? address.houseNumber+" "+address.street
                : (address.street != null) ? address.street
                : (address.city != null) ? address.city
                : (address.state != null) ? address.state : address.country;
    }

    public String getFullAddress() {
        return getLabel()+ ", " + getCity();
    }

}
