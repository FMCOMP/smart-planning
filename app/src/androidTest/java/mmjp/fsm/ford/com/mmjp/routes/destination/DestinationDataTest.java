package mmjp.fsm.ford.com.mmjp.routes.destination;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Looper;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.destination.DestinationActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class DestinationDataTest {
    String userInput;

    @Rule
    public IntentsTestRule<DestinationActivity> intentsTestRule = new IntentsTestRule<>(DestinationActivity.class);
    public ActivityTestRule<DestinationActivity> mDestinationActivity = new ActivityTestRule<>(DestinationActivity.class);

    @Before
    public void init(){
        userInput = "test";
    }

    @Test
    public void vaildateInputInteraction() {

        //find the destination edit text and type in the destination
        onView(withId(R.id.destination_edit_text)).perform(typeText(userInput), closeSoftKeyboard());
        onView(withId(R.id.destination_button)).perform(click());
    }

    @Test
    public void vaildateInputResponse() {
        vaildateInputInteraction();

        if(userInput.length() > 0){
            Intent resultData = new Intent();
            String destinationAddress = userInput;
            resultData.putExtra("destination", destinationAddress);
            Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
            intending(toPackage("mmjp.fsm.ford.com.mmjp.origin")).respondWith(result);

            onView(withId(R.id.destination_edit_text)).check(matches(withText(destinationAddress)));
        } else {
            Looper.prepare();
            onView(withText("Please enter destination address")).check(matches(isDisplayed()));
        }
    }


}
