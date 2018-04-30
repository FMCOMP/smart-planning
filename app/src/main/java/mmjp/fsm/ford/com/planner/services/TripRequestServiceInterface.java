package mmjp.fsm.ford.com.planner.services;

import io.reactivex.subjects.AsyncSubject;
import mmjp.fsm.ford.com.planner.entity.GeoCode;
import mmjp.fsm.ford.com.planner.entity.Trip;
import mmjp.fsm.ford.com.planner.modules.search.entity.RouteOptions;

public interface TripRequestServiceInterface {
    AsyncSubject<GeoCode> fetchGeoCode(String locationId);
    AsyncSubject<RouteOptions> fetchRoutesV1(Trip tripBody);
    AsyncSubject<RouteOptions> fetchRoutesV2(Trip tripBody);
}