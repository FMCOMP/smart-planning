package mmjp.fsm.ford.com.mmjp.app.route.providers.lyft.rider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;
import mmjp.fsm.ford.com.mmjp.app.route.providers.lyft.rider.entity.LyftAuth;
import mmjp.fsm.ford.com.mmjp.app.route.providers.lyft.rider.entity.LyftClientToken;
import retrofit2.http.GET;

public interface LyftRiderContract {

    LyftRiderContract.View mListener = null;

    interface Service {
        void onCreate(Activity mView);
        AsyncSubject<LyftClientToken> fetchClientToken();
        AsyncSubject<LyftClientToken> fetchClientAuth(LyftAuth auth);
    }

    interface Presenter {
        void deepLinkIntoLyft();
        void startRiderSession();
        void onCreate(Activity activity);
        void openLink(Activity activity, String link);
    }

    interface View {
        void onResume();
        void onCreate(Bundle savedInstanceState);
        void onRiderRequestListener(LyftRiderContract.View listener);
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    interface Query {
        @GET("/oauth/authorize")
        Observable<LyftClientToken> fetchClientAuth(@retrofit2.http.Query("client_id") String clientId,
                                                          @retrofit2.http.Query("response_type") String responseType,
                                                          @retrofit2.http.Query("scope") String[] scope,
                                                          @retrofit2.http.Query("state") String state);

        Observable<LyftClientToken> fetchClientToken();
    }
}
