package mmjp.fsm.ford.com.mmjp.app.route.providers.lyft.rider.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import mmjp.fsm.ford.com.mmjp.app.route.providers.lyft.rider.utils.LyftConstants;

public class LyftAuth implements Serializable {

    @SerializedName("client_id")
    private String clientID;

    @SerializedName("response_type")
    private String responseType;

    @SerializedName("scope")
    private String[] scopes;

    @SerializedName("state")
    private String state;

    public LyftAuth() {
        responseType = "code";
        clientID = LyftConstants.CLIENT_ID;
        scopes = new String[] {"public", "rides.read","offline", "rides.request", "profile"};
    }

    public String getClientID() {
        return clientID;
    }

    public String getResponseType() {
        return responseType;
    }

    public String getState() {
        return state;
    }

    public String[] getScopes() {
        return scopes;
    }
}
