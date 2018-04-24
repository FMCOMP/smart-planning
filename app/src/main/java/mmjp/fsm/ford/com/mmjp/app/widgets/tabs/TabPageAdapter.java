package mmjp.fsm.ford.com.mmjp.app.widgets.tabs;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TabPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Activity mActivity;
    Fragment[] mFragments;
    ArrayList<TabEntity> mTabs;

    public TabPageAdapter(Activity activity, FragmentManager fm, ArrayList<TabEntity> tabs, int tabCount) {
        super(fm);

        mTabs = tabs;
        mActivity = activity;
        mNumOfTabs = tabCount;
        mFragments = new Fragment[tabCount];

        for (int i = 0; i < mNumOfTabs; i++){
            int index = tabs.get(i).getTabIndex();
            mFragments[index] = TabViewFragment.newInstance(mActivity,tabs.get(i));
            System.out.println("Tab Added " + mFragments.toString());
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
       return mFragments[position];
    }
}
