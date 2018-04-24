package mmjp.fsm.ford.com.mmjp.accounts;
import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.users.home.LoginActivity;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

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
@MediumTest
public class LoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivity = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void LoginUITest() {
        String emailAddress = "firstname.lastname@g.com";
        String password = "password";

        //find the firstname edit text and type in the first name
        onView(ViewMatchers.withId(R.id.input_email)).perform(typeText(emailAddress), closeSoftKeyboard());

        //find the lastname edit text and type in the last name
        onView(withId(R.id.input_password)).perform(typeText(password), closeSoftKeyboard());

        //click the signup button
        onView(withId(R.id.btn_signin)).perform(click());

      /*  //check that we can see the success screen with success message
        String successString = InstrumentationRegistry.getTargetContext().getString(R.string.text_sign_up_successful);
        onView(withId(R.id.text_view_status_message)).check(matches(allOf(withText(successString), isDisplayed())));*/
    }

}
