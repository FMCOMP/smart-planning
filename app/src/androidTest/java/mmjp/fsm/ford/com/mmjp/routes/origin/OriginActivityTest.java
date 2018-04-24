package mmjp.fsm.ford.com.mmjp.routes.origin;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.origin.OriginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;

/**
 * Created by userpmp on 2/8/18.
 */

/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class OriginActivityTest {

    @Rule
    public ActivityTestRule<OriginActivity> mOriginActivityTest = new ActivityTestRule<>(OriginActivity.class);

    @Test
    public void  OriginUITest() {
        String currentLocation = "currentLocation";
        String destination = "destination";

        //find the destination edit text and type in the destination
        onView(ViewMatchers.withId(R.id.origin_edit_text)).perform(typeText(currentLocation), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.destination_edit_text)).perform(typeText(destination), closeSoftKeyboard());

    }

}
