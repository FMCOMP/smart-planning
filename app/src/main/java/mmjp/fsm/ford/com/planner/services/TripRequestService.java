package mmjp.fsm.ford.com.planner.services;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import mmjp.fsm.ford.com.planner.entity.GeoCode;
import mmjp.fsm.ford.com.planner.entity.Trip;
import mmjp.fsm.ford.com.planner.modules.search.entity.RouteOptions;
import mmjp.fsm.ford.com.services.api.APIRequestService;
import retrofit2.Retrofit;

public class TripRequestService implements TripRequestServiceInterface {
    Retrofit service;

    public static TripRequestService newInstance() {
        return new TripRequestService();
    }

    private TripRequestService(){
        APIRequestService apiRequestService = new APIRequestService();
        apiRequestService.setAPIRequest("v2");

        service = apiRequestService.getPCFRequestService();
    }

    @Override
    public AsyncSubject<GeoCode> fetchGeoCode(String locationId) {
        AsyncSubject<GeoCode> subject = AsyncSubject.create();

        TripQuery query = service.create(TripQuery.class);

        query.fetchGeoCode(locationId).subscribeOn(Schedulers.io()).subscribe(subject);

        return subject;
    }

    @Override
    public AsyncSubject<RouteOptions> fetchRoutesV1(Trip tripBody) {

        AsyncSubject<RouteOptions> subject = AsyncSubject.create();
        TripQuery query = service.create(TripQuery.class);
        query.fetchRoutesV1(tripBody).subscribeOn(Schedulers.io()).subscribe(subject);

        return subject;
    }

    @Override
    public AsyncSubject<RouteOptions> fetchRoutesV2(Trip tripBody) {

        AsyncSubject<RouteOptions> subject = AsyncSubject.create();
        TripQuery query = service.create(TripQuery.class);
        query.fetchRoutesV2(tripBody).subscribeOn(Schedulers.io()).subscribe(subject);

        return subject;
    }
}
