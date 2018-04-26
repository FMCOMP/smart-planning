package mmjp.fsm.ford.com;

import android.app.Application;
import android.content.Context;

import io.fabric.sdk.android.Fabric;
import com.crashlytics.android.Crashlytics;
import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.metrics.MetricsManager;

public class MainActivity extends Application {

    public Context context;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}