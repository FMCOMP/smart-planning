package mmjp.fsm.ford.com.mmjp.routes.destination;


import android.app.Activity;
import android.widget.Spinner;

import mmjp.fsm.ford.com.mmjp.R;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DestinationInterfaceTest.class, DestinationDataTest.class, DestinationServiceTest.class})
public class DestinationTestSuite {
//    Activity activity;
//
//    // BEGIN_INCLUDE (destination_activity)
//    public DestinationTestSuite() {
//        // BEGIN_INCLUDE (launch_activity)
//        activity = DestinationActivity.getActivity();
//        // END_INCLUDE (launch_activity)
//    }
//
//    // BEGIN_INCLUDE (destination_mapping)
//    public void testDestinationMappingView() {
//
//        final int TEST_SPINNER_POSITION_1 = DestinationActivity.WEATHER_PARTLY_CLOUDY;
//
//        // BEGIN_INCLUDE (launch_activity)
//        Activity activity = getActivity();
//        // END_INCLUDE (launch_activity)
//
//        // BEGIN_INCLUDE (here_map_integration)
//        // Set spinner to test position 1
//        final Spinner spinner1 = (Spinner) activity.findViewById(R.id.spinner);
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                // Attempts to manipulate the UI must be performed on a UI thread.
//                // Calling this outside runOnUiThread() will cause an exception.
//                //
//                // You could also use @UiThreadTest, but activity lifecycle methods
//                // cannot be called if this annotation is used.
//                spinner1.requestFocus();
//
//            }
//        });
//
//        // BEGIN_INCLUDE (relaunch_activity)
//        // Close the activity
//        activity.finish();
//        setActivity(null);  // Required to force creation of a new activity
//    }
//    // END_INCLUDE (destination_mapping)
//
//    // BEGIN_INCLUDE (destination_input)
//    public void testDestinationUserInput(){
//        // Relaunch the activity
//        activity = this.getActivity();
//        // END_INCLUDE (relaunch_activity)
//
//        // BEGIN_INCLUDE (destination_input)
//        final Spinner spinner1 = (Spinner) activity.findViewById(R.id.spinner);
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                // Attempts to manipulate the UI must be performed on a UI thread.
//                // Calling this outside runOnUiThread() will cause an exception.
//                //
//                // You could also use @UiThreadTest, but activity lifecycle methods
//                // cannot be called if this annotation is used.
//                spinner1.requestFocus();
//                spinner1.setSelection(TEST_SPINNER_POSITION_1);
//            }
//        });
//
//        // Verify that the spinner was saved at position 2
//        final Spinner spinner3 = (Spinner) activity.findViewById(R.id.spinner);
//        currentPosition = spinner3.getSelectedItemPosition();
//        assertEquals(TEST_SPINNER_POSITION_2, currentPosition);
//
//        // Close the activity
//        activity.finish();
//        setActivity(null);  // Required to force creation of a new activity
//
//        // Relaunch the activity
//        activity = this.getActivity();
//        // END_INCLUDE (relaunch_activity)
//    }
//    // END_INCLUDE (destination_input)
//    // END_INCLUDE (destination_activity)destination_activity

}
