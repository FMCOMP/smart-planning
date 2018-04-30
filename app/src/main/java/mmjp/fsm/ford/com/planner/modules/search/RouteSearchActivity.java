package mmjp.fsm.ford.com.planner.modules.search;

import android.app.DialogFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import mmjp.fsm.ford.com.R;
import mmjp.fsm.ford.com.planner.modules.summary.RouteSummaryDialogFragment;
import mmjp.fsm.ford.com.planner.widgets.tabs.TabViewFragment;
import mmjp.fsm.ford.com.planner.widgets.tabs.TabPageAdapter;
import mmjp.fsm.ford.com.planner.entity.Location;
import mmjp.fsm.ford.com.planner.entity.Preferences;
import mmjp.fsm.ford.com.planner.entity.Trip;
import mmjp.fsm.ford.com.planner.modules.search.entity.RouteOptions;
import mmjp.fsm.ford.com.planner.modules.search.services.RouteModel;
import mmjp.fsm.ford.com.planner.modules.search.services.RouteModelInterface;
import mmjp.fsm.ford.com.planner.widgets.tabs.TabEntity;
import mmjp.fsm.ford.com.planner.widgets.timepicker.TimePickerFragment;


public class RouteSearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Trip mTrip;

    // Activity View Single-Instance Binder
    private Unbinder unbinder = null;

    // Check user location service status
    private ViewPager viewPager;

    // Check user location service status
    private TabLayout tabLayout;

    // Check user location service status
    private RouteModel mRequest;

    private RouteModelInterface mListener;

    private ArrayList<TabEntity> mTabs = new ArrayList<>();

    @BindView(R.id.time_clock) ImageView departureTimeBtn;
    @BindView(R.id.origin_edit_text) EditText originEditText;
    @BindView(R.id.time_display_text) TextView displayTimeText;
    @BindView(R.id.destination_edit_text) EditText destinationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_routes);
        unbinder = ButterKnife.bind(this);

        // Initiate Address Suggestion Model
        mTrip = new Trip();
        mRequest = new RouteModel(this);

        mTrip.setTripOrigin((Location) getIntent().getSerializableExtra("origin"));
        mTrip.setTripDepartureTime((long) getIntent().getSerializableExtra("departure"));
        mTrip.setTripDestination((Location) getIntent().getSerializableExtra("destination"));
        mTrip.setTripPreferences((Preferences) getIntent().getSerializableExtra("preferences"));

        originEditText.setText(mTrip.getTripOrigin().getUserLocation());
        destinationEditText.setText(mTrip.getTripDestination().getUserLocation());

        mRequest.fetchRouteOptions(mTrip);
        mRequest.onRouteOptionsListener(new RouteModelInterface() {
            @Override
            public void OnRouteOptionsListener(boolean isSet, RouteOptions routeOptions) {
                onReturnedRouteOptions(RouteSearchConstants.ROUTE_TAB_TITLES, routeOptions);
            }
        });
    }

    /** Begin Setup for Tab Component **/
    private ViewPager getTabViewPager(ArrayList<TabEntity> tabs){
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabPageAdapter adapter = new TabPageAdapter(this,getSupportFragmentManager(), tabs, tabs.size());
        viewPager.setAdapter(adapter);

        return viewPager;
    }

    private void onTabLayoutSetup(final ArrayList<TabEntity> tabs){
        viewPager = getTabViewPager(tabs);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        for (int i = 0; i < tabs.size(); i++){
            boolean isSelected = tabs.get(i).getTabIndex() == 0;
            TabLayout.Tab mTab = getTab(tabLayout, isSelected, tabs.get(i), null);
            tabLayout.addTab(mTab, tabs.get(i).getTabIndex());
        }

        tabLayout.setupWithViewPager(viewPager, false);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        tabLayout.setTabTextColors(android.R.color.black, getResources().getColor(R.color.colorAccent));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.setText(tabs.get(tab.getPosition()).getTabName());
                tab.setIcon(tabs.get(tab.getPosition()).getTabIconSelected());
                tabLayout.setSelectedTabIndicatorColor(tabs.get(tab.getPosition()).getTabIndicator());
                tabLayout.setTabTextColors(android.R.color.black, tabs.get(tab.getPosition()).getTabIndicator());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setText(tabs.get(tab.getPosition()).getTabName());
                tab.setIcon(tabs.get(tab.getPosition()).getTabIconUnSelected());
                tabLayout.setTabTextColors(android.R.color.black, tabs.get(tab.getPosition()).getTabIndicator());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.setText(tabs.get(tab.getPosition()).getTabName());
                tab.setIcon(tabs.get(tab.getPosition()).getTabIconSelected());
                tabLayout.setSelectedTabIndicatorColor(tabs.get(tab.getPosition()).getTabIndicator());
                tabLayout.setTabTextColors(android.R.color.black, tabs.get(tab.getPosition()).getTabIndicator());
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public void onReturnedRouteOptions(String[] tabTitles, RouteOptions data) {
        for (int i = 0; i < tabTitles.length; i++) {

            TabEntity tab = new TabEntity();

            switch (tabTitles[i]) {
                case "Price":
                    tab.setTabIndex(i);
                    tab.setTabName(tabTitles[i]);
                    tab.setTabData(data.getPriceRouteOptions());
                    tab.setTabIndicator(getResources().getColor(R.color.colorAccent));
                    tab.setTabIconSelected(getResources().getDrawable(R.drawable.ic_money_circle_white));
                    tab.setTabIconUnSelected(getResources().getDrawable(R.drawable.ic_price));
                    break;
                case "Speed":
                    tab.setTabIndex(i);
                    tab.setTabName(tabTitles[i]);
                    tab.setTabData(data.getTimeRouteOptions());
                    tab.setTabIndicator(getResources().getColor(R.color.lyftPrimary));
                    tab.setTabIconSelected(getResources().getDrawable(R.drawable.ic_timer_white));
                    tab.setTabIconUnSelected(getResources().getDrawable(R.drawable.ic_timer_black));
                    break;
                case "Connections":
                    tab.setTabIndex(i);
                    tab.setTabName(tabTitles[i]);
                    tab.setTabData(data.getConnectionsRouteOptions());
                    tab.setTabIndicator(getResources().getColor(R.color.colorWarning));
                    tab.setTabIconSelected(getResources().getDrawable(R.drawable.ic_map_white));
                    tab.setTabIconUnSelected(getResources().getDrawable(R.drawable.ic_map_black));
                    break;
            }

            mTabs.add(tab.getTabIndex(), tab);
            tab.setTabFragment(TabViewFragment.newInstance(this, tab));

            if(i == (tabTitles.length - 1)){
                System.out.println("Tabs Ready for Layout");
                onTabLayoutSetup(mTabs);
            }
        }
    }
    /** End Setup for Tab Component **/

    @OnClick(R.id.time_clock)
    public void showTimePickerDialog(View v) {
        DialogFragment timePicker = TimePickerFragment.newInstance(this, displayTimeText);
        timePicker.show(getFragmentManager(), "timePicker");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private TabLayout.Tab getTab(TabLayout tabLayout, boolean isSelected, TabEntity data, @Nullable TabLayout.Tab tab){
        if(isSelected && (tab != null)){
            tabLayout.setTabTextColors(android.R.color.black, data.getTabIndicator());
            tabLayout.setSelectedTabIndicatorColor(data.getTabIndicator());
            tab.setIcon(data.getTabIconSelected());

            System.out.println("Tab " + tab.getText() + " Ready for reSelect.");

        } else {
            tab = tabLayout.newTab();
            tab.setText(data != null ? data.getTabName() : null);
            tab.setTag(data != null ? data.getTabIndicator() : null);
            tab.setIcon(data != null ? data.getTabIconUnSelected() : null);

            System.out.println("Tab " + tab.getText() + " is Ready.");
        }

        return tab;
    }
}

