package mmjp.fsm.ford.com.services.testfairy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.testfairy.TestFairy;

import java.util.HashMap;
import java.util.Map;

public class TestFairyActivity  {

    final private String TEST_FAIRY_KEY = "01fb56344d09821a22e84b91347b92857c8ddb99";

    public TestFairyActivity(Activity activity) {
        TestFairy.begin(activity, TEST_FAIRY_KEY);
    }

    public void fetchUserData(String emailAddr, String username){
        Map<String, Object> traits = new HashMap<String, Object>();
        traits.put(TestFairy.IDENTITY_TRAIT_EMAIL_ADDRESS, emailAddr);
        TestFairy.identify(username, traits);
    }
}
