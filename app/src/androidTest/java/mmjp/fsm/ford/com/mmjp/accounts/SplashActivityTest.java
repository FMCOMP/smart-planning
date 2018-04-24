package mmjp.fsm.ford.com.mmjp.accounts;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mmjp.fsm.ford.com.mmjp.app.users.home.SplashActivity;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void splashActivityTest() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
