package mmjp.fsm.ford.com.mmjp.services.heremaps.model;

import java.lang.ref.WeakReference;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.FragmentManager;
import android.widget.TextView;
import android.widget.Toast;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;

import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapRoute;
import com.here.android.mpa.mapping.MapState;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.routing.CoreRouter;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.Router;
import com.here.android.mpa.routing.RoutingError;
import com.here.android.positioning.StatusListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.LocationDataSourceHERE;

import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.services.heremaps.entity.MapEntity;

public class MapPositioningModel implements PositioningManager.OnPositionChangedListener, Map.OnTransformListener  {

    // Map embedded in the map fragment
    private Map map;

    // Map embedded in the map fragment
    private Activity mActivity;

    // Map embedded in the map fragment
    private MapEntity mEntity;

    private MapRoute mRoute;

    // Map fragment embedded in this activity
    private MapFragment mapFragment;

    // Map fragment embedded in this activity
    private GeoCoordinate mCoordinates;

    // Map embedded in the map fragment
    private FragmentManager mFragmentManager;

    // Positioning manager instance
    private PositioningManager mPositioningManager;

    // HERE location data source instance
    private LocationDataSourceHERE mHereLocation;

    // Flag that indicates whether maps is being transformed
    private boolean mTransforming;

    // Callback that is called when transforming ends
    private Runnable mPendingUpdate;

    // Text view instance for showing location information
    private TextView mLocationInfo;

    /**
     * Getting User Current Location information.
     */
    public MapPositioningModel(Activity activity){
        mFragmentManager = activity.getFragmentManager();

          /* Locate the mapFragment UI element */
        mapFragment = (MapFragment) mFragmentManager.findFragmentById(R.id.map_layout);

        if (mapFragment != null) {

            /* Initialize the MapFragment, results will be given via the called back. */
            mapFragment.init(new OnEngineInitListener() {

                @Override
                public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                    if (error == OnEngineInitListener.Error.NONE) {
                        mPositioningManager = PositioningManager.getInstance();
                        mPositioningManager.start(PositioningManager.LocationMethod.GPS_NETWORK);

                        System.out.println("isActive " + mPositioningManager.isActive());

                        map = mapFragment.getMap();
                        map.addTransformListener(MapPositioningModel.this);
                        map.setCenter(mPositioningManager.getPosition().getCoordinate(), Map.Animation.LINEAR);

                        double maxZoom = map.getMaxZoomLevel();
                        double minZoom = map.getMinZoomLevel();

                        map.setZoomLevel((maxZoom + minZoom)/2);

                        mHereLocation = LocationDataSourceHERE.getInstance(new StatusListener() {
                            @Override
                            public void onOfflineModeChanged(boolean offline) {
                                // called when offline mode changes
                            }

                            @Override
                            public void onAirplaneModeEnabled() {
                                System.out.println("Airport Enabled");
                            }

                            @Override
                            public void onWifiScansDisabled() {
                                // called when Wi-Fi scans are disabled
                            }

                            @Override
                            public void onBluetoothDisabled() {
                                // called when Bluetooth is disabled
                            }

                            @Override
                            public void onCellDisabled() {
                                System.out.println("Cell Network Disabled");
                            }

                            @Override
                            public void onGnssLocationDisabled() {
                                // called when GPS positioning is disabled
                            }

                            @Override
                            public void onNetworkLocationDisabled() {
                                System.out.println("Network Location Disabled");
                            }

                            @Override
                            public void onServiceError(StatusListener.ServiceError serviceError) {
                                // called on HERE service error
                            }

                            @Override
                            public void onPositioningError(PositioningError positioningError) {
                                System.out.println(positioningError);
                            }
                        });

                        if (mHereLocation == null) {
                            Toast.makeText(mActivity.getBaseContext(), "LocationDataSourceHERE.getInstance(): failed, exiting", Toast.LENGTH_LONG).show();
                            return;
                        }

                        mPositioningManager.setDataSource(mHereLocation);
                        mPositioningManager.addListener(new WeakReference<PositioningManager.OnPositionChangedListener>(MapPositioningModel.this));

                        System.out.println("Coordinates " + mPositioningManager.getPosition().getCoordinate());
                        System.out.println("isValid " + mPositioningManager.hasValidPosition());

                        // start position updates, accepting GPS, network or indoor positions
                        if (mPositioningManager.isActive()) {
                            mapFragment.getPositionIndicator().setVisible(true);

                        } else {
                            Toast.makeText(mActivity.getBaseContext(), "PositioningManager.start: failed, exiting", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(mActivity.getBaseContext(), "onEngineInitializationCompleted: error: " + error + ", exiting", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    /* Creates a route from 4350 Still Creek Dr to Langley BC with highways disallowed */
    private void createRoute() {
        /* Initialize a CoreRouter */
        CoreRouter coreRouter = new CoreRouter();

        /* Initialize a RoutePlan */
        RoutePlan routePlan = new RoutePlan();

        /*
         * Initialize a RouteOption.HERE SDK allow users to define their own parameters for the
         * route calculation,including transport modes,route types and route restrictions etc.Please
         * refer to API doc for full list of APIs
         */
        RouteOptions routeOptions = new RouteOptions();
        /* Other transport modes are also available e.g Pedestrian */
        routeOptions.setTransportMode(RouteOptions.TransportMode.CAR);
        /* Disable highway in this route. */
        routeOptions.setHighwaysAllowed(false);
        /* Calculate the shortest route available. */
        routeOptions.setRouteType(RouteOptions.Type.SHORTEST);
        /* Calculate 1 route. */
        routeOptions.setRouteCount(1);
        /* Finally set the route option */
        routePlan.setRouteOptions(routeOptions);

        /* Define waypoints for the route */
        /* START: 4350 Still Creek Dr */
        RouteWaypoint startPoint = new RouteWaypoint(new GeoCoordinate(49.259149, -123.008555));
        /* END: Langley BC */
        RouteWaypoint destination = new RouteWaypoint(new GeoCoordinate(49.073640, -122.559549));

        /* Add both waypoints to the route plan */
        routePlan.addWaypoint(startPoint);
        routePlan.addWaypoint(destination);

        /* Trigger the route calculation,results will be called back via the listener */
        coreRouter.calculateRoute(routePlan,
                new Router.Listener<List<RouteResult>, RoutingError>() {
                    @Override
                    public void onProgress(int i) {
                        /* The calculation progress can be retrieved in this callback. */
                    }

                    @Override
                    public void onCalculateRouteFinished(List<RouteResult> routeResults,
                                                         RoutingError routingError) {
                        /* Calculation is done.Let's handle the result */
                        if (routingError == RoutingError.NONE) {
                            if (routeResults.get(0).getRoute() != null) {
                                /* Create a MapRoute so that it can be placed on the map */
                                mRoute = new MapRoute(routeResults.get(0).getRoute());

                                /* Show the maneuver number on top of the route */
                                mRoute.setManeuverNumberVisible(true);

                                /* Add the MapRoute to the map */
                                map.addMapObject(mRoute);

                                /*
                                 * We may also want to make sure the map view is orientated properly
                                 * so the entire route can be easily seen.
                                 */
                                GeoBoundingBox gbb = routeResults.get(0).getRoute()
                                        .getBoundingBox();
                                map.zoomTo(gbb, Map.Animation.NONE,
                                        Map.MOVE_PRESERVE_ORIENTATION);
                            } else {
                                Toast.makeText(mActivity,
                                        "Error:route results returned is not valid",
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mActivity,
                                    "Error:route calculation returned error code: " + routingError,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void onStopMapUpdate(){
        if (mPositioningManager != null) {
            mPositioningManager.stop();
        }
    }

    public void onPauseMapUpdate(){
        if (mPositioningManager != null) {
            mPositioningManager.removeListener(this);
        }
    }

    public void onResumeMapUpdate(){
        if (mPositioningManager != null) {
            mPositioningManager.start(PositioningManager.LocationMethod.GPS_NETWORK);
        }
    }

    public MapFragment getMapFragment(){
        return mapFragment;
    }

    public GeoCoordinate getMapCoordinates(){
        return mCoordinates;
    }

    /**
     * Update location information.
     *
     * @param geoPosition Latest geo position update.
     */
    private void updateLocationInfo(PositioningManager.LocationMethod locationMethod, GeoPosition geoPosition) {
        if (mLocationInfo == null) {
            return;
        }

        final StringBuffer sb = new StringBuffer();
        final GeoCoordinate coord = geoPosition.getCoordinate();

        sb.append("Type: ").append(String.format(Locale.US, "%s\n", locationMethod.name()));
        sb.append("Coordinate:").append(String.format(Locale.US, "%.6f, %.6f\n", coord.getLatitude(), coord.getLongitude()));

        if (coord.getAltitude() != GeoCoordinate.UNKNOWN_ALTITUDE) {
            sb.append("Altitude:").append(String.format(Locale.US, "%.2fm\n", coord.getAltitude()));
        }

        if (geoPosition.getHeading() != GeoPosition.UNKNOWN) {
            sb.append("Heading:").append(String.format(Locale.US, "%.2f\n", geoPosition.getHeading()));
        }

        if (geoPosition.getSpeed() != GeoPosition.UNKNOWN) {
            sb.append("Speed:").append(String.format(Locale.US, "%.2fm/s\n", geoPosition.getSpeed()));
        }

        if (geoPosition.getBuildingName() != null) {
            sb.append("Building: ").append(geoPosition.getBuildingName());
            if (geoPosition.getBuildingId() != null) {
                sb.append(" (").append(geoPosition.getBuildingId()).append(")\n");
            } else {
                sb.append("\n");
            }
        }

        if (geoPosition.getFloorId() != null) {
            sb.append("Floor: ").append(geoPosition.getFloorId()).append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);
        mLocationInfo.setText(sb.toString());
    }

    @Override
    public void onMapTransformEnd(MapState mapState) {
        mTransforming = false;
        if (mPendingUpdate != null) {
            mPendingUpdate.run();
            mPendingUpdate = null;
        }
    }

    @Override
    public void onMapTransformStart() {
        mTransforming = true;
    }

    @Override
    public void onPositionFixChanged(PositioningManager.LocationMethod locationMethod, PositioningManager.LocationStatus locationStatus) {
        //System.out.println("Position Fixed Changed " + locationStatus.ordinal());
    }

    @Override
    public void onPositionUpdated(final PositioningManager.LocationMethod locationMethod, final GeoPosition geoPosition, final boolean mapMatched) {
        final GeoCoordinate coordinate = geoPosition.getCoordinate();

        if (mTransforming) {
            mPendingUpdate = new Runnable() {
                @Override
                public void run() {
                    onPositionUpdated(locationMethod, geoPosition, mapMatched);
                }
            };
        } else {
            map.setCenter(coordinate, Map.Animation.BOW);
            updateLocationInfo(locationMethod, geoPosition);
        }

        mCoordinates = coordinate;
    }
}
