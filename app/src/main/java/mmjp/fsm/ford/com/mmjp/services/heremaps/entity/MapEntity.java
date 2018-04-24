package mmjp.fsm.ford.com.mmjp.services.heremaps.entity;


import android.app.Activity;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.MapFragment;

import mmjp.fsm.ford.com.mmjp.services.heremaps.model.MapPositioningModel;

public class MapEntity {

    // Map fragment embedded in this activity
    private MapPositioningModel mUtility;

    public MapEntity(Activity activity){
        mUtility = new MapPositioningModel(activity);
    }

    public void resumeUpdates(){
        mUtility.onResumeMapUpdate();
    }

    public void pauseUpdates(){
        mUtility.onPauseMapUpdate();
    }

    public void stopUpdates(){
        mUtility.onStopMapUpdate();
    }

    public MapFragment view() {
        return mUtility.getMapFragment();
    }

    public GeoCoordinate coordinates(){
        return mUtility.getMapCoordinates();
    }
}
