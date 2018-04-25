package mmjp.fsm.ford.com.mmjp.app.trip.modules.destination;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.here.android.mpa.common.GeoCoordinate;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Location;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Trip;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.origin.OriginView;
import mmjp.fsm.ford.com.mmjp.app.widgets.timepicker.TimePickerFragment;
import mmjp.fsm.ford.com.mmjp.services.heremaps.entity.MapEntity;


public class DestinationView extends AppCompatActivity implements DestinationContract, NavigationView.OnNavigationItemSelectedListener{

    Trip trip;

    // Default Departure Date in Time
    MapEntity map;

    // Default Departure Date in Time
    Boolean hasOrigin;

    // Default Departure Date in Time
    Location origin;

    // Default Departure Date in Time
    private Date selectedDate;

    // Default Departure Time
    private long selectedTime;

    // Activity View Single-Instance Binder
    private Unbinder unbinder = null;

    // Flag that indicates whether maps is being transformed
    private boolean leaveNow = true;

    // Check user location service status
    private DrawerLayout mDrawerLayout;

    DestinationPresenter presenter;

    @BindView(R.id.time_display_text) EditText displayTimeText;
    @BindView(R.id.destination_edit_text) EditText destinationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        unbinder = ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.app_toolbar_layout);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.destination_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.app_nav_layout);
        navigationView.setNavigationItemSelectedListener(this);

        selectedDate = Calendar.getInstance().getTime();
        destinationEditText.setFocusable(false);

    }

    @Override
    protected void onStart(){
        super.onStart();

       presenter = DestinationPresenter.newInstance(this, trip);
    }

    @OnClick(R.id.time_clock)
    public void showTimePickerDialog(View v) {
        DialogFragment timePicker = TimePickerFragment.newInstance(this, displayTimeText);
        timePicker.show(getFragmentManager(), "timePicker");
    }

    @OnClick(R.id.destination_edit_text)
    public void onDestinationTextClick() {
       
        if (map.coordinates() != null) {

            origin.setUserCoordinates(map.coordinates().getLatitude(), map.coordinates().getLongitude());

            Intent intent = new Intent(DestinationView.this, OriginView.class);

            //intent.putExtra(JPUtils.DESTINATION, destinationEditText.getText());
            intent.putExtra("origin", origin);

            if(leaveNow){
                intent.putExtra("departure", new Date().getTime());
            } else {
                intent.putExtra("departure", selectedTime);
            }

            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    class ViewHolder {

        ViewHolder(View view) {
            ButterKnife.bind(this, (android.view.View) view);
        }
    }

}
