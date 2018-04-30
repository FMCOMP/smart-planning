package mmjp.fsm.ford.com.enroute.lyft.rider.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LyftClientToken implements Serializable {

    @SerializedName("grant_type")
    private String grantType;

    @SerializedName("code")
    private String code;

    public void setCode(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getGrantType() {
        return grantType;
    }
}
