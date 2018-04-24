package mmjp.fsm.ford.com.mmjp.app.trip.modules.route.services;

import android.app.Activity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.GeoCode;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Location;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Trip;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.route.entity.RouteOptions;
import mmjp.fsm.ford.com.mmjp.app.trip.services.TripRequestService;

/**
 * Created by troger56 on 3/21/18.
 */

public class RouteModel {

    private Activity mActivity;
    private RouteOptions routeOptions;
    private RouteModelInterface mListener;

    public RouteModel(Activity activity){
        mActivity = activity;
    }

    public void fetchRouteOptions(final Trip routeRequest) {
        TripRequestService.newInstance().fetchRoutesV2(routeRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RouteOptions>() {
                    @Override
                    public void accept(RouteOptions routes) throws Exception {
                        routeOptions = routes;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mListener.OnRouteOptionsListener(true, routeOptions);
                    }
                });

    }

    public void fetchGeoCode(final Trip trip, String locationId, final String locationField, final String fullAddress) {
        TripRequestService.newInstance().fetchGeoCode( locationId )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Consumer<GeoCode>() {
                    @Override
                    public void accept(GeoCode geocode) throws Exception {
                        Location location = new Location();

                        location.setUserLocation(fullAddress);
                        location.setUserCoordinates(geocode.getLatitude(), geocode.getLongitude());

                        switch (locationField){
                            case "destination":
                                trip.setTripDestination(location);
                                break;
                            case "origin":
                                trip.setTripOrigin(location);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    public void onRouteOptionsListener(RouteModelInterface listener){
            mListener = listener;
    }
}
