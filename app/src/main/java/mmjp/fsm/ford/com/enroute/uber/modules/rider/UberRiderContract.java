package mmjp.fsm.ford.com.enroute.uber.modules.rider;

import android.content.Intent;
import android.os.Bundle;

import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.rides.client.SessionConfiguration;

/**
 * Created by troger56 on 3/21/18.
 */

public interface UberRiderContract {

    UberRiderContract.View mListener = null;

    interface Service {
        void onCreate(UberRiderContract.View view);
        void loadProfileInfo(LoginManager loginManager);
    }

    interface Presenter {
        SessionConfiguration onCreate();
    }

    interface View {
        void onResume();
        void onCreate(Bundle savedInstanceState);
        void onRiderRequestListener(UberRiderContract.View listener);
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
