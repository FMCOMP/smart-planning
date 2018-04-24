package mmjp.fsm.ford.com.mmjp.app.widgets.address.services;

import java.util.List;

import io.reactivex.Observable;
import mmjp.fsm.ford.com.mmjp.app.widgets.address.entity.AddressSuggestion;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by troger56 on 3/21/18.
 */

public interface AddressSuggestionQuery {

    @GET("address-suggestions")
    Observable<List<AddressSuggestion>> fetchAddressSuggestion(@Query("location") String inputText,
                                                               @Query("currentLat") String currentLat,
                                                               @Query("currentLng") String currentLng);
}
