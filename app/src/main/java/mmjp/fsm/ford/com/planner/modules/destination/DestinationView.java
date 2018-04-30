package mmjp.fsm.ford.com.planner.modules.destination;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.here.android.mpa.common.GeoCoordinate;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.FeedbackManager;
import net.hockeyapp.android.UpdateManager;
import net.hockeyapp.android.metrics.MetricsManager;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.planner.entity.Location;
import mmjp.fsm.ford.com.planner.entity.Trip;
import mmjp.fsm.ford.com.planner.modules.origin.OriginView;
import mmjp.fsm.ford.com.planner.widgets.timepicker.TimePickerFragment;
import mmjp.fsm.ford.com.planner.vendors.heremaps.entity.MapEntity;
import mmjp.fsm.ford.com.profile.splash.SplashActivity;


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

    private DrawerLayout mDrawerLayout;
    DestinationPresenter presenter;

    @BindView(R.id.feedback_button) Button feedbackButton;
    @BindView(R.id.destination_edit_text) EditText destinationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        unbinder = ButterKnife.bind(this);

        mDrawerLayout = findViewById(R.id.destination_drawer_layout);
        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(@NonNull android.view.View drawerView, float slideOffset) {

                    }

                    @Override
                    public void onDrawerOpened(@NonNull android.view.View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(@NonNull android.view.View drawerView) {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        Toolbar toolbar = findViewById(R.id.app_toolbar_layout);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = findViewById(R.id.app_nav_layout);
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    // set item as selected to persist highlight
                    menuItem.setChecked(true);
                    // close drawer when item is tapped
                    mDrawerLayout.closeDrawers();

                    // Add code here to update the UI based on the item selected
                    // For example, swap UI fragments here

                    return true;
                }
            });

        toggle.syncState();

        selectedDate = Calendar.getInstance().getTime();
        destinationEditText.setFocusable(false);

        checkForUpdates();
        MetricsManager.register(getApplication());
        MetricsManager.trackEvent("Application Entry");

        FeedbackManager.register(this);

        Button feedbackButton = (Button) findViewById(R.id.feedback_button);
        FeedbackManager.setActivityForScreenshot(this);
        feedbackButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                FeedbackManager.showFeedbackActivity(DestinationView.this);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

       presenter = DestinationPresenter.newInstance(this, trip);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();

        checkForCrashes();
    }

    private void checkForCrashes() {
        CrashManager.register(this);
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this);
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

}
