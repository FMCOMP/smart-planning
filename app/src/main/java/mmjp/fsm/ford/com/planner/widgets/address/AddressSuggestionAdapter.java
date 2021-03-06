package mmjp.fsm.ford.com.planner.widgets.address;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
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

import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.planner.entity.GeoCode;
import mmjp.fsm.ford.com.planner.entity.Location;
import mmjp.fsm.ford.com.planner.services.TripRequestService;
import mmjp.fsm.ford.com.planner.widgets.address.entity.AddressSuggestion;
import mmjp.fsm.ford.com.planner.widgets.address.services.AddressSuggestionModel;


public class AddressSuggestionAdapter extends RecyclerView.Adapter<AddressSuggestionAdapter.ViewHolder> {
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_address_suggestion, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        position = viewHolder.getAdapterPosition();
        viewHolder.additionalAddressLabel.setText(suggestions.get(position).getCity());
        viewHolder.streetAddressLabel.setText(suggestions.get(position).getLabel());

        final int finalPosition = position;
        viewHolder.addressSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSuggestion = suggestions.get(finalPosition);
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

