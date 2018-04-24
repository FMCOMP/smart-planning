package mmjp.fsm.ford.com.mmjp.app.widgets.address;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.GeoCode;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Location;
import mmjp.fsm.ford.com.mmjp.app.trip.services.TripRequestService;
import mmjp.fsm.ford.com.mmjp.app.widgets.address.entity.AddressSuggestion;
import mmjp.fsm.ford.com.mmjp.app.widgets.address.services.AddressSuggestionModel;


public class AddressSuggestionAdapter extends RecyclerView.Adapter<AddressSuggestionAdapter.ViewHolder> {
    private View view;
    private TextView mField;
    private Location mLocation;
    private AddressSuggestionModel mModel;
    private AddressSuggestion selectedSuggestion;

    private List<AddressSuggestion> suggestions;

    public AddressSuggestionAdapter(AddressSuggestionModel model, TextView field, List<AddressSuggestion> addressSuggestion) {
        mModel = model;
        mField = field;
        suggestions  = addressSuggestion;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.list_item_address_suggestion, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.additionalAddressLabel.setText(suggestions.get(position).getCity());
        viewHolder.streetAddressLabel.setText(suggestions.get(position).getLabel());

        viewHolder.addressSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSuggestion = suggestions.get(position);
                mField.setText(selectedSuggestion.getFullAddress());
                fetchGeoCode(selectedSuggestion.locationId);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    public void fetchGeoCode(String locationId){
        final GeoCode[] code = {new GeoCode()};
        TripRequestService.newInstance().fetchGeoCode(locationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GeoCode>() {
                    @Override
                    public void accept(GeoCode geocode) throws Exception {
                        mLocation = new Location();
                        mLocation.setUserLocation(selectedSuggestion.getFullAddress());
                        mLocation.setUserCoordinates(geocode.getLatitude(), geocode.getLongitude());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mModel.setMLocation(mLocation);
                    }
                });
    }

    public Location getLocation(){
        System.out.println("Location " + mLocation.getUserLongitude());
        return mLocation;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.history_logo) ImageView historyLogo;
        @BindView(R.id.address_list_item) RelativeLayout addressSuggestion;
        @BindView(R.id.street_address_field) TextView streetAddressLabel;
        @BindView(R.id.additional_detail_field) TextView additionalAddressLabel;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

