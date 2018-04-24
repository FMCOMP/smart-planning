package mmjp.fsm.ford.com.mmjp.app.trip.modules.summary;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.route.entity.RouteSegment;

public class RouteSegmentListAdapter extends RecyclerView.Adapter<RouteSegmentListAdapter.ViewHolder> {

    private ArrayList<RouteSegment> mSegments;

    @Override
    public int getItemCount() {
        return mSegments.size();
    }

    RouteSegmentListAdapter(ArrayList<RouteSegment> segments){
        mSegments = segments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.print("Creating Summary Adapter ");

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_route_segment, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        //set the holder with needed values from the MODEL car like below and delete the below line
        viewHolder.segmentTo.setText(mSegments.get(position).getSegmentStartAddress().getUserLocation());
        viewHolder.segmentFrom.setText(mSegments.get(position).getSegmentEndAddress().getUserLocation());

        viewHolder.segmentMode.setText(mSegments.get(position).getSegmentMode());
        viewHolder.segmentDirection.setText(mSegments.get(position).getSegmentMode());
        viewHolder.segmentNumber.setText(mSegments.get(position).getSegmentRouteName());
        viewHolder.segmentDistance.setText(mSegments.get(position).getSegmentDistance().getTransitDistance() + mSegments.get(position).getSegmentDistance().getTransitUnitMeasurement());
        viewHolder.segmentDestination.setText(mSegments.get(position).getSegmentEndAddress().getUserLocation());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.segment_to) TextView segmentTo;
        @BindView(R.id.segment_mode) TextView segmentMode;
        @BindView(R.id.segment_from) TextView segmentFrom;
        @BindView(R.id.segment_bus_number) TextView segmentNumber;
        @BindView(R.id.segment_direction) TextView segmentDirection;
        @BindView(R.id.segment_distance) TextView segmentDistance;
        @BindView(R.id.segment_destination) TextView segmentDestination;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
