package mmjp.fsm.ford.com.planner.modules.summary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.planner.entity.Location;
import mmjp.fsm.ford.com.planner.modules.search.entity.Route;
import mmjp.fsm.ford.com.planner.modules.search.entity.RouteSegment;
import mmjp.fsm.ford.com.planner.vendors.heremaps.entity.MapEntity;

public class RouteSummaryActivity extends AppCompatActivity {

    private Route mRoute;
    private MapEntity map;
    private Location mOrigin;
    private Unbinder unbinder;
    private FragmentManager manager;
    BottomSheetBehavior sheetBehavior;
    Boolean isMinimumHeightEnabled = false;
    private ArrayList<RouteSegment> mSegments;
    BottomSheetDialogFragment mDialogFragment;

    @BindView(R.id.route_info_btn) ImageView infoBtn;
    @BindView(R.id.route_fare_amt) TextView routeFareText;
    @BindView(R.id.transit_mode_text) TextView routeModeText;
    @BindView(R.id.total_bus_time) TextView routeBusDuration;
    @BindView(R.id.total_walk_time) TextView walkAvgDistance;
    @BindView(R.id.route_arrival_text) TextView arrivalTimeText;
    @BindView(R.id.route_departure_text) TextView departureTimeText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Boolean hasExtra = intent.hasExtra("segments");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        setRoute(intent);

        // Create a new Fragment to be placed in the activity layout
        mDialogFragment = RouteSummaryDialogFragment.newInstance(this, mRoute);
        mDialogFragment.show(transaction, mDialogFragment.getTag());

        setContentView(R.layout.activity_route_summary);
        unbinder = ButterKnife.bind(this);
        initMapFragment();

        routeModeText.setText(mRoute.getRouteMode());
        routeFareText.setText("$" + mRoute.getRouteFare());
        arrivalTimeText.setText("Arrival : " + intent.getStringExtra("arrival"));
        departureTimeText.setText("Departure : " + intent.getStringExtra("departure"));


        System.out.println("Fragment Initiated: " );

//        RouteDistance walkTime = (RouteDistance) intent.getSerializableExtra("walkTime");
//        RouteDistance travelTime = (RouteDistance) intent.getSerializableExtra("travelTime");
//        String walkDistance = getRouteDistance(walkTime.getTransitDistance(), walkTime.getTransitUnitMeasurement());
//        String travelDistance = getRouteDistance(travelTime.getTransitDistance(), travelTime.getTransitUnitMeasurement());
//        walkAvgDistance.setText("( " + walkDistance + " min avg walk )");
//        routeBusDuration.setText(travelDistance + "MIN");
//        System.out.println("Routes Received " + mSegments.size());

    }

    private String getRouteDistance(String distance, String unit){
        return distance + unit.toUpperCase();
    }

    private void setRoute(Intent intent){
        mRoute = new Route();

        mRoute.setRouteMode(intent.getStringExtra("mode"));
        mRoute.setRouteFare(Double.parseDouble(intent.getStringExtra("fare")));
        mRoute.setRouteSegments((ArrayList<RouteSegment>) intent.getSerializableExtra("segments"));
    }

    // Begin Map Fragment Functionality
    public void initMapFragment(){
        map = new MapEntity(this);
        map.view();

        mOrigin = initCoordinates();
    }

    public Location initCoordinates(){
        return new Location();
    }
}
