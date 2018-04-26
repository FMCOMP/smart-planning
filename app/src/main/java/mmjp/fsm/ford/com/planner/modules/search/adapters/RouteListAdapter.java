package mmjp.fsm.ford.com.planner.modules.search.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.planner.modules.search.entity.Route;
import mmjp.fsm.ford.com.planner.modules.summary.RouteSummaryActivity;

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.ViewHolder> {

    private Activity mActivity;
    private ArrayList<Route> mList;
    private BottomSheetBehavior sheetBehavior;
    private BottomSheetDialogFragment mBottomSheet;

    public RouteListAdapter(Activity activity, ArrayList<?> list){
        mActivity = activity;
        mList = (ArrayList<Route>) list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_routes, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        //set the holder with needed values from the MODEL car like below and delete the below line
        String walkAvgDistance = getRouteDistance(mList.get(position).getRouteAvgWalkDistance().getTransitDistance(), mList.get(position).getRouteAvgWalkDistance().getTransitUnitMeasurement());

        viewHolder.walkAvgDistance.setText("( " + walkAvgDistance + " min avg walk )");
        viewHolder.routeBusDuration.setText(mList.get(position).getRouteDuration() + "MIN");
        viewHolder.arrivalTimeText.setText("ETA " + mList.get(position).getRouteArrivalTime());
        viewHolder.departureTimeText.setText("Departure " + mList.get(position).getRouteDeparture());
        viewHolder.routeFareText.setText("$" + mList.get(position).getRouteFare());
        viewHolder.routeModeText.setText(mList.get(position).routeMode);
        viewHolder.infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRouteSelection(position);
            }
        });
    }

    private void onRouteSelection(int position){
        Route route = mList.get(position);

        Intent intent = new Intent(mActivity, RouteSummaryActivity.class);

        intent.putExtra("fare", route.getRouteFare());
        intent.putExtra("travel", route.getRouteDuration());
        intent.putExtra("leave", route.getRouteDeparture());
        intent.putExtra("segments", route.getRouteSegments());
        intent.putExtra("arrival", route.getRouteArrivalTime());
        intent.putExtra("walk", route.getRouteAvgWalkDistance());

        mActivity.startActivity(intent);
    }

    private String getRouteDistance(String distance, String unit){
        return distance + unit.toUpperCase();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.route_info_btn) ImageView infoBtn;
        @BindView(R.id.route_fare_amt) TextView routeFareText;
        @BindView(R.id.transit_mode_text) TextView routeModeText;
        @BindView(R.id.total_bus_time) TextView routeBusDuration;
        @BindView(R.id.total_walk_time) TextView walkAvgDistance;
        @BindView(R.id.route_arrival_text) TextView arrivalTimeText;
        @BindView(R.id.route_departure_text) TextView departureTimeText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}

