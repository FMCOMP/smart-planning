package mmjp.fsm.ford.com.planner.modules.summary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;
import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.planner.modules.search.entity.Route;
import mmjp.fsm.ford.com.planner.modules.search.entity.RouteSegment;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     RouteSummaryDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link RouteSummaryDialogFragment.Listener}.</p>
 */
public class RouteSummaryDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";

    private Route mRoute;
    private Unbinder unbinder;
    private Integer mItemCount;
    private Activity mActivity;
    private ArrayList<RouteSegment> mSegments;
    private boolean isMinimumHeightEnabled = false;
    private BottomSheetBehavior bottomSheetBehavior;

    @BindView(R.id.start_btn) Button tripConfirmBtn;

    public static RouteSummaryDialogFragment newInstance(Activity activity, Route route) {
        Bundle args = new Bundle();

        RouteSummaryDialogFragment fragment = new RouteSummaryDialogFragment();

        fragment.mRoute = route;
        fragment.mActivity = activity;
        fragment.mSegments = route.getRouteSegments();
        fragment.mItemCount = route.getRouteSegmentCount();

        fragment.setMinimumHeightEnabled(fragment.isMinimumHeightEnabled);

        args.putInt(ARG_ITEM_COUNT, fragment.mItemCount);
        fragment.setArguments(args);
        
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        System.out.println("Fragment Attached 1: " );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_route_summary_dialog, container, false);

        View bottomSheet = v.findViewById(R.id.route_details_layout);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        return v;
    }

    public void setAdapter(View view) {
        final RecyclerView recyclerView = view.findViewById(R.id.route_segment_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RouteSegmentListAdapter(mSegments));

        System.out.println("Fragment List View Created 3: " );
    }

    @Override
    @SuppressLint("RestrictedApi")
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View view = View.inflate(getContext(), R.layout.fragment_route_summary_dialog, null);
        dialog.setContentView(view);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
        layoutParams.setBehavior(bottomSheetBehavior);

        final CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {

            ((BottomSheetBehavior) behavior).setHideable(false);
            ((BottomSheetBehavior) behavior).setPeekHeight(275);
            ((BottomSheetBehavior) behavior).setState(BottomSheetBehavior.STATE_COLLAPSED);
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {

                    switch (newState) {
                        case BottomSheetBehavior.STATE_EXPANDED:
                            System.out.println("State : " + newState + " Expanded" );
//                            ((BottomSheetBehavior) behavior).setState(newState);
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            System.out.println("State : " + newState + " Collapsed" );
                            ((BottomSheetBehavior) behavior).setPeekHeight(275);
                            break;
                        case BottomSheetBehavior.STATE_DRAGGING:
                            System.out.println("State : " + newState + " Hidden" );
                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            System.out.println("State : " + newState + " Settling" );
                            break;
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    System.out.println("Slide State : " + slideOffset + " " + bottomSheet.getHeight());
                }
            });
        }

        setAdapter(view);

        System.out.println("Fragment Setup 3: " );
    }

    public void setMinimumHeightEnabled(boolean flag) {
        isMinimumHeightEnabled = flag;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
