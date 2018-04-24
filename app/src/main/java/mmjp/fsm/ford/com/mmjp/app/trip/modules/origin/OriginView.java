package mmjp.fsm.ford.com.mmjp.app.trip.modules.origin;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Location;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Preferences;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Trip;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.route.RouteSearchActivity;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.route.services.RouteModel;
import mmjp.fsm.ford.com.mmjp.app.widgets.address.entity.AddressSuggestion;
import mmjp.fsm.ford.com.mmjp.app.widgets.address.services.AddressSuggestionModel;
import mmjp.fsm.ford.com.mmjp.app.widgets.timepicker.TimePickerFragment;
import mmjp.fsm.ford.com.mmjp.utils.JPUtils;

/**
 * Created by troger56 on 3/18/18.
 */
public class OriginView extends AppCompatActivity {
    private Trip trip;
    private Location origin;
    private RouteModel request;
    private Location destination;
    private Unbinder unbinder = null;
    private AddressSuggestionModel suggestions;
    private List<AddressSuggestion> suggestionList;
    private long selectedTime = new Date().getTime();

    @BindView(R.id.time_clock) ImageView departureTimeBtn;
    @BindView(R.id.origin_edit_text) EditText originEditText;
    @BindView(R.id.time_display_text) TextView displayTimeText;
    @BindView(R.id.destination_edit_text) EditText destinationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origin);

        unbinder = ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        // Set Origin Activity Services
        trip = Trip.newInstance();
        trip.setTripDepartureTime(selectedTime);
        trip.setTripPreferences(Preferences.newInstance());

        // Save Route Departure Time
        long departure = (long) bundle.get("departure");

        // Populate Current Location
        originEditText.setText("Current Location");
        originEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               suggestions.fetchAddressSuggestions("origin", originEditText, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        trip.setTripOrigin((Location) getIntent().getSerializableExtra("origin"));

        // Initiate Destination Location Variable
        destination = new Location();
        destinationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                suggestions.fetchAddressSuggestions("destination", destinationEditText, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        destinationEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 66){
                    if(originEditText.getText() == null){
                        Toast.makeText(getBaseContext(), "Please, enter your current location for accuracy routing options", Toast.LENGTH_LONG).show();
                    } else {
                        onNextScreen();
                    }
                }
                return false;
            }
        });


        // Initiate Address Suggestion Model
        request = new RouteModel(this);

        // Initiate Address Suggestion Model
        suggestions = new AddressSuggestionModel(this, trip);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!originEditText.isFocused() ) {
            JPUtils.setFocusForView(destinationEditText);
            JPUtils.showKeyBoard(this, destinationEditText);
        }
    }

    private void onNextScreen(){
//        System.out.println("Trip Details " + trip.getTripDestination().getUserLongitude() );

        final Intent intent = new Intent(this, RouteSearchActivity.class);

        intent.putExtra("preferences", trip.getTripPreferences());
        intent.putExtra("destination", trip.getTripDestination());
        intent.putExtra("departure", trip.getTripDepartureTime());
        intent.putExtra("origin", trip.getTripOrigin());

        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.time_clock)
    public void showTimePickerDialog(View v) {
        DialogFragment timePicker = TimePickerFragment.newInstance(this, displayTimeText);
        timePicker.show(getFragmentManager(), "timePicker");
    }

    // Begin Action Button Functionality
    @OnClick(R.id.time_clock)
    protected void onDepartureTimeClick() {
        destinationEditText.setText( originEditText.getText().toString());
        originEditText.setText( destinationEditText.getText().toString());

    }

    @OnFocusChange(R.id.origin_edit_text)
    protected void onOriginEditTextClick() {

    }

    @OnFocusChange(R.id.destination_edit_text)
    protected void onDestinationEditTextClick() {

    }
    // End Action Button Functionality

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    static class ViewHolder {
        
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
