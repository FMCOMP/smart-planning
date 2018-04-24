package mmjp.fsm.ford.com.mmjp.app.route.providers.uber.modules.rider;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

import mmjp.fsm.ford.com.mmjp.app.route.providers.uber.utils.UberConstants;

import static com.uber.sdk.android.core.utils.Preconditions.checkNotNull;
import static com.uber.sdk.android.core.utils.Preconditions.checkState;

public class UberRiderPresenter implements UberRiderContract.Presenter {

    private SessionConfiguration configuration;
    private Boolean hasSessionConfiguration = false;

    @Override
    public SessionConfiguration onCreate(){

       startRiderSession();
       validateConfiguration(configuration);

       UberSdk.initialize(configuration);

       return configuration;
    }

    private SessionConfiguration startRiderSession(){
        if(!hasSessionConfiguration) {
            SessionConfiguration.Builder builder = new SessionConfiguration.Builder();
            builder.setClientId(UberConstants.CLIENT_ID);
            builder.setServerToken(UberConstants.SERVER_TOKEN);
            builder.setRedirectUri(UberConstants.REDIRECT_URI);
            builder.setClientSecret(UberConstants.CLIENT_SECRET);
            builder.setScopes(Arrays.asList(UberConstants.SCOPES));
            builder.setEnvironment(SessionConfiguration.Environment.SANDBOX);

            configuration = builder.build();
            hasSessionConfiguration = true;
        }

        return configuration;
    }

    private void validateConfiguration(SessionConfiguration configuration) {
        String nullError = "%s must not be null";
        String sampleError = "Please update your %s in the gradle.properties of the project before " +
                "using the Uber SDK Sample app. For a more secure storage location, " +
                "please investigate storing in your user home gradle.properties ";

        checkNotNull(configuration, String.format(nullError, "SessionConfiguration"));
        checkNotNull(configuration.getClientId(), String.format(nullError, "Client ID"));
        checkNotNull(configuration.getRedirectUri(), String.format(nullError, "Redirect URI"));
        checkState(!configuration.getClientId().equals("insert_your_client_id_here"),
                String.format(sampleError, "Client ID"));
        checkState(!configuration.getRedirectUri().equals("insert_your_redirect_uri_here"),
                String.format(sampleError, "Redirect URI"));
    }
}
