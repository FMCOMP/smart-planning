package mmjp.fsm.ford.com;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;
import android.util.Patterns;

import com.optimizely.Optimizely;
import io.fabric.sdk.android.Fabric;
import com.crashlytics.android.Crashlytics;
import com.testfairy.TestFairy;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.metrics.MetricsManager;

public class MainActivity extends Application {

    public Context context;
    String userID;

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());
        TestFairy.begin(this, "01fb56344d09821a22e84b91347b92857c8ddb99");

        // TODO: Move this to where you establish a user session
       getUserAccount();

    }

    private void logUser(String name, String group, String email) {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier(group);
        Crashlytics.setUserEmail(email);
        Crashlytics.setUserName(name);

        TestFairy.setUserId(name);
    }

    private void getUserAccount(){
        Account[] accounts = AccountManager.get(MainActivity.this).getAccounts();
        for (Account account : accounts) {
            if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches()) {
                userID = account.name;
                logUser(account.name,"internal", "email@test.com");
                break;
            }
        }
    }
}