package mmjp.fsm.ford.com.mmjp.app.widgets.tabs;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import mmjp.fsm.ford.com.mmjp.app.widgets.tabs.TabViewFragment;

/**
 * Created by troger56 on 3/28/18.
 */

public class TabEntity implements Serializable {

    private int tabIndex;
    private String tabName;

    private int tabIndicator;
    private ArrayList<?> tabData;
    private Drawable tabIconSelected;
    private Drawable tabIconUnSelected;
    private TabViewFragment tabFragment;

    public String getTabName() {
        return tabName;
    }

    public void setTabIndex(int index) {
        tabIndex = index;
    }

    public void setTabData(ArrayList<?> data) {
        tabData = data;
    }

    public void setTabName(String name) {
        tabName = name;
    }

    public void setTabIconSelected(Drawable icon){
        tabIconSelected = icon;
    }

    public void setTabIconUnSelected(Drawable icon){
        tabIconUnSelected = icon;
    }

    public void setTabFragment(TabViewFragment fragment) {
        tabFragment = fragment;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public ArrayList<?> getTabData() {
        return tabData;
    }

    public int getTabIndicator() {
        return tabIndicator;
    }

    public TabViewFragment getTabFragment() {
        return tabFragment;
    }

    public void setTabIndicator(int color) {
        tabIndicator = color;
    }

    public Drawable getTabIconSelected(){
        return tabIconSelected;
    }

    public Drawable getTabIconUnSelected(){
        return tabIconUnSelected;
    }
}
