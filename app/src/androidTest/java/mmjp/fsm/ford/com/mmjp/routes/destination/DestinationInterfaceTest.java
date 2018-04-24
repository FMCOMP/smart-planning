package mmjp.fsm.ford.com.mmjp.routes.destination;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.destination.DestinationActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by troger56 on 2/15/18.
 */

public class DestinationInterfaceTest {
    @Rule
    public ActivityTestRule<DestinationActivity> mDestinationActivity = new ActivityTestRule<>(DestinationActivity.class);

    @Before
    public void init(){}

    @Test
    public void vaildateDestinationField(){
        onView(withId(R.id.destination_edit_text)).check(matches(isDisplayed()));

    }

}
