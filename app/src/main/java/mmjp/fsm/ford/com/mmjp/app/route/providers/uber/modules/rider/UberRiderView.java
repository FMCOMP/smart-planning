package mmjp.fsm.ford.com.mmjp.app.route.providers.uber.modules.rider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.rides.client.SessionConfiguration;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.profile.registration.accounts.AccountLinkingView;

public class UberRiderView extends AppCompatActivity implements UberRiderContract.View {

    private Unbinder unbinder;

    private LoginCallback loginCallback;
    private UberRiderContract.Presenter rider;
    private AccessTokenManager tokenManager;
    private LoginManager loginManager;
    private SessionConfiguration configuration;

    private static final String LOG_TAG = "UberRiderView";
    private static final int CUSTOM_BUTTON_REQUEST_CODE = 1113;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unbinder = ButterKnife.bind(this);

        rider = new UberRiderPresenter();
        configuration = rider.onCreate();

        loginCallback = new LoginCallback() {
            @Override
            public void onLoginCancel() {
                Toast.makeText(UberRiderView.this, R.string.user_cancels_message, Toast.LENGTH_LONG).show();
                System.out.println("Auth Cancelled");
            }

            @Override
            public void onLoginError(@NonNull AuthenticationError error) {
                Toast.makeText(UberRiderView.this, getString(R.string.login_error_message, error.name()), Toast.LENGTH_LONG).show();
                System.out.println("Auth Error");
            }

            @Override
            public void onLoginSuccess(@NonNull AccessToken accessToken) {

                if(loginManager.isAuthenticated()){
                    Toast.makeText(UberRiderView.this, "Success", Toast.LENGTH_LONG).show();
                    System.out.println("Access Token Success" + accessToken.getToken());

                    Intent intent = new Intent(UberRiderView.this, AccountLinkingView.class);
                    intent.putExtra("uber_token", accessToken.getToken());

                    startActivity(intent);
                }
            }

            @Override
            public void onAuthorizationCodeReceived(@NonNull String authorizationCode) {
                Toast.makeText(UberRiderView.this, getString(R.string.authorization_code_message, authorizationCode), Toast.LENGTH_LONG).show();
                System.out.println("Auth Received");
            }
        };
        tokenManager = new AccessTokenManager(this);
        loginManager = new LoginManager(tokenManager, loginCallback);

        loginManager.login(UberRiderView.this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(loginManager.isAuthenticated()) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(LOG_TAG, String.format("onActivityResult requestCode:[%s] resultCode [%s]", requestCode, resultCode));
        System.out.println("Session Manager 2 ");

        loginManager.onActivityResult(this, requestCode, resultCode, data);
    }

    public void onRiderRequestListener(UberRiderContract.View mListener){

    }

    public void onLinkNewAccount(){
        AccessTokenManager tokenManager = loginManager.getAccessTokenManager();
//            System.out.println("Session Manager " + tokenManager.getAccessToken().getToken().toString());

        Intent intent = new Intent(UberRiderView.this, AccountLinkingView.class);
        AccessToken token = tokenManager.getAccessToken();
        if(token != null){
            intent.putExtra("hasAuth", true);
            intent.putExtra("token", token.getToken());
            intent.putExtra("token_type", token.getTokenType());
            intent.putExtra("expires_in", token.getExpiresIn());
        } else {
            intent.putExtra("hasToken", false);
        }

        startActivity(intent);
    }

}
