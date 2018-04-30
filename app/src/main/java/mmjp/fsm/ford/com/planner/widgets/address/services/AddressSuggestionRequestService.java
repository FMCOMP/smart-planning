package mmjp.fsm.ford.com.planner.widgets.address.services;

import java.util.List;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import mmjp.fsm.ford.com.planner.widgets.address.entity.AddressSuggestion;
import mmjp.fsm.ford.com.services.api.APIRequestService;
import retrofit2.Retrofit;

public class AddressSuggestionRequestService implements AddressSuggestionRequestServiceInterface {
    private Retrofit service;

    public static AddressSuggestionRequestService newInstance() {
        return new AddressSuggestionRequestService();
    }

    private AddressSuggestionRequestService(){
        APIRequestService apiRequestService = new APIRequestService();
        apiRequestService.setAPIRequest("v2");
        service = apiRequestService.getPCFRequestService();
    }

    @Override
    public AsyncSubject<List<AddressSuggestion>> fetchAddressSuggestion(String input, String latitude, String longitude) {
        AsyncSubject<List<AddressSuggestion>> subject = AsyncSubject.create();

        AddressSuggestionQuery query = service.create(AddressSuggestionQuery.class);

        query.fetchAddressSuggestion(input, latitude, longitude).subscribeOn(Schedulers.io()).subscribe(subject);

        return subject;
    }
}
