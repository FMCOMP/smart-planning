package mmjp.fsm.ford.com.planner.services;

import io.reactivex.Observable;
import mmjp.fsm.ford.com.planner.entity.GeoCode;
import mmjp.fsm.ford.com.planner.entity.Trip;
import mmjp.fsm.ford.com.planner.modules.search.entity.RouteOptions;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by unnathwoona on 2/14/18.
 */

public interface TripQuery {

    @GET("geocode")
    Observable<GeoCode> fetchGeoCode(@Query("locationId") String locationId);

    @POST("routing-options")
    Observable<RouteOptions> fetchRoutesV1(@Body Trip body);

    @POST("routing-options/v1")
    Observable<RouteOptions> fetchRoutesV2(@Body Trip body);
}