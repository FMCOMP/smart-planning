package mmjp.fsm.ford.com.mmjp.app.trip.modules.destination;

import android.app.Activity;

import mmjp.fsm.ford.com.mmjp.app.trip.entity.Location;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Trip;

public interface DestinationContract {

    interface Presenter {
        void initMapFragment();
        boolean getGPSStatus();
        void checkPermissions();
        Location initCoordinates();
        void requestPermissions();
        void requestLocationService();
        void onRequestPermissionsResult();
        DestinationPresenter newInstance(Activity activity, Trip trip);
        void setCoordinates(Location location, Double latitude, Double longitude);
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
