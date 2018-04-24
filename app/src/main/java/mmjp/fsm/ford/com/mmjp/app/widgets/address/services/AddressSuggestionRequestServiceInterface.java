package mmjp.fsm.ford.com.mmjp.app.widgets.address.services;

import java.util.List;

import io.reactivex.subjects.AsyncSubject;
import mmjp.fsm.ford.com.mmjp.app.widgets.address.entity.AddressSuggestion;

/**
 * Created by troger56 on 3/21/18.
 */

public interface AddressSuggestionRequestServiceInterface {
    public AsyncSubject<List<AddressSuggestion>> fetchAddressSuggestion(String inputText, String latitude, String longitude);
}
