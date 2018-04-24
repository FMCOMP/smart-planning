package mmjp.fsm.ford.com.mmjp.app.widgets.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mmjp.fsm.ford.com.mmjp.R;
import mmjp.fsm.ford.com.mmjp.app.trip.modules.route.adapters.RouteListAdapter;

public class TabViewFragment extends Fragment {

    public TabEntity mTab;
    private Activity mActivity;

    public static TabViewFragment newInstance(Activity activity, TabEntity tab){
        TabViewFragment fragment = new TabViewFragment();
        fragment.mActivity = activity;
        fragment.mTab = tab;

        return fragment;
    }

    private void setAdapter(View view, TabEntity tab) {
        RecyclerView mRecyclerView = view.findViewById(R.id.routes_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new RouteListAdapter(mActivity, tab.getTabData()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_tab_view, container, false);
        setAdapter(view, mTab);

        return view;
    }
}
