package mmjp.fsm.ford.com.planner.widgets.address.services;

import android.app.Activity;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.planner.entity.Location;
import mmjp.fsm.ford.com.planner.entity.Trip;
import mmjp.fsm.ford.com.planner.widgets.address.entity.AddressSuggestion;
import mmjp.fsm.ford.com.planner.widgets.address.AddressSuggestionAdapter;

public class AddressSuggestionModel {

    private Trip mTrip;
    private String mField;
    private Activity mActivity;
    private AddressSuggestionAdapter adapter;
    public boolean hasSuggestionList = false;
    private List<AddressSuggestion> suggestionList;

    public AddressSuggestionModel(Activity activity, Trip trip){
        mTrip = trip;
        mActivity = activity;
    }

    public void fetchAddressSuggestions(final String field, final TextView fieldView, String inputText) {
        AddressSuggestionRequestService.newInstance().fetchAddressSuggestion(inputText, mTrip.getTripOrigin().getUserLatitude(), mTrip.getTripOrigin().getUserLongitude())
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<List<AddressSuggestion>>() {
                   @Override
                   public void accept(final List<AddressSuggestion> addressSuggestions) throws Exception {
                       RecyclerView mRecyclerView = mActivity.findViewById(R.id.address_suggestion_list);
                       mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getBaseContext()));
                       adapter = new AddressSuggestionAdapter(AddressSuggestionModel.this, fieldView, addressSuggestions);
                       mRecyclerView.setAdapter(adapter);
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                       throwable.printStackTrace();
                   }
               }, new Action() {
                   @Override
                   public void run() throws Exception {
                       mField = field;
                   }
               });
    }

    public void setMLocation(Location location){
       switch (mField){
           case "destination":
               mTrip.setTripDestination(location);
               break;
           case "origin":
               mTrip.setTripOrigin(location);
               break;
       }
    }

    public Trip getTrip(){
        if (mField.equals("destination")){
            System.out.println(mTrip.getTripDestination());
        }
        return mTrip;
    }

    public List<AddressSuggestion> getSuggestionList(){
        return suggestionList;
    }
}
