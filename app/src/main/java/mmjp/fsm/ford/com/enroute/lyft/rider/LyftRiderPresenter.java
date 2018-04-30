package mmjp.fsm.ford.com.enroute.lyft.rider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.lyft.networking.ApiConfig;

import mmjp.fsm.ford.com.enroute.lyft.rider.utils.LyftConstants;

public class LyftRiderPresenter implements LyftRiderContract.Presenter {

    private Activity mActivity;
    private ApiConfig configuration;
    private Boolean hasSessionConfiguration = false;
    private static final String TAG = "lyft:Rider";
    private static final String LYFT_PACKAGE = "me.lyft.android";

    @Override
    public void onCreate(Activity activity){
        mActivity = activity;

       startRiderSession();
    }

    @Override
    public void startRiderSession(){
        if(!hasSessionConfiguration) {
            ApiConfig.Builder builder = new ApiConfig.Builder();
            builder.setClientId(LyftConstants.CLIENT_ID);
            builder.setClientToken(LyftConstants.CLIENT_TOKEN);

            hasSessionConfiguration = true;
            configuration = builder.build();
        }
    }

    @Override
    public void deepLinkIntoLyft() {
        if (isPackageInstalled(mActivity, LYFT_PACKAGE)) {
            //This intent will help you to launch if the package is already installed
            openLink(mActivity, "lyft://");
            Log.d(TAG, "Lyft is already installed on your phone.");
        } else {
            openLink(mActivity, "https://www.lyft.com/signup/SDKSIGNUP?clientId=" + LyftConstants.CLIENT_ID + "&sdkName=android_direct");
            Log.d(TAG, "Lyft is not currently installed on your phone..");
        }
    }

    @Override
    public void openLink(Activity activity, String link) {
        Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
        playStoreIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        playStoreIntent.setData(Uri.parse(link));
        activity.startActivity(playStoreIntent);
    }

    private static boolean isPackageInstalled(Context context, String packageId) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageId, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // ignored.
        }
        return false;
    }
}
