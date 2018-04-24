package mmjp.fsm.ford.com.mmjp.app.interfaces;

import android.support.annotation.NonNull;

/**
 * Created by troger56 on 3/23/18.
 */

public interface LocationServiceInterface {

    boolean getGPSStatus();

    void checkPermissions();

    void requestPermissions();

    void requestLocationService();

    void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults);
}
