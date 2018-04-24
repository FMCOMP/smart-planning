package mmjp.fsm.ford.com.mmjp.app.trip.services;

import io.reactivex.subjects.AsyncSubject;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.GeoCode;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Trip;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.route.entity.RouteOptions;

public interface TripRequestServiceInterface {
    AsyncSubject<GeoCode> fetchGeoCode(String locationId);
    AsyncSubject<RouteOptions> fetchRoutesV1(Trip tripBody);
    AsyncSubject<RouteOptions> fetchRoutesV2(Trip tripBody);
}