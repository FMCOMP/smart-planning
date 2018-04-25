package mmjp.fsm.ford.com.mmjp.app.trip.modules.destination;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.here.android.mpa.common.GeoCoordinate;

import butterknife.Unbinder;
import mmjp.fsm.ford.com.mmjp.app.interfaces.LocationServiceInterface;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Location;
import mmjp.fsm.ford.com.mmjp.app.trip.entity.Trip;
import mmjp.fsm.ford.com.mmjp.services.heremaps.entity.MapEntity;

public class DestinationPresenter implements LocationServiceInterface {
    Trip mTrip;
    
    Activity mActivity;
    
    // Single-Instance of Map Fragment
    private MapEntity map;

    // Permission Request Code ID
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    // Permissions that need to be explicitly requested from end user.
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // Activity View Single-Instance Binder
    private Unbinder unbinder = null;

    // Check user location service status
    boolean GpsStatus;

    public static DestinationPresenter newInstance(Activity activity, Trip trip){
        DestinationPresenter presenter = new DestinationPresenter();
        presenter.mActivity = activity;
        presenter.mTrip = trip;

        presenter.checkPermissions();

        return presenter;
    }

    // Begin Application Permission Functionality
    @Override
    public boolean getGPSStatus(){
        LocationManager locationManager = (LocationManager) mActivity.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * Checks the dynamically controlled permissions and requests missing
     * permissions from end user.
     */
    @Override
    public void checkPermissions() {
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(mActivity.getBaseContext(), "To delivery accurate route options for the upcoming journey, location services need to be turned on", Toast.LENGTH_LONG).show();
                requestPermissions();
            } else {
                // No explanation needed; request the permission
                requestPermissions();
            }
        } else {
            if(!getGPSStatus()){
                requestLocationService();
            }
        }

        initMapFragment();
    }

    @Override
    public void requestPermissions(){
        ActivityCompat.requestPermissions(mActivity, REQUIRED_SDK_PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS);
    }

    @Override
    public void requestLocationService(){
        new AlertDialog.Builder(mActivity)
                .setTitle("Enable Location Service")
                .setMessage("For better location accuracy, please enable location services. Would you like to enable your location services now?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mActivity.startActivity(intent1);

                        Toast.makeText(mActivity, "Location Services Enabled", Toast.LENGTH_LONG).show();
                        initMapFragment();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(mActivity, "Location Services Disabled", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(mActivity, "Required permission '" + permissions[index] + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        mActivity.finish();
                        return;
                    }
                }
                break;
        }
        initMapFragment();
    }
    // End Application Permission Functionality

    // Begin Map Fragment Functionality
    private void initMapFragment(){
        map = new MapEntity(mActivity);
        map.view();
    }

    public MapEntity getMap() {
        return map;
    }

    public Location initCoordinates(){
        return new Location();
    }

    public void setCoordinates(Location location, Double latitude, Double longitude){
        location.setUserCoordinates(latitude,longitude);
        mTrip.setTripOrigin(location);
    }
    // End Map Fragment Functionality
}
