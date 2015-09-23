package com.sevencrayons.compass;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.util.Log;
import android.widget.Toast;


public class CompassActivity extends ActionBarActivity {

    private static final String TAG = "CompassActivity";

    private Compass compass;

    GPSTracker gps;
    double latitude;
    double longitude;

    private EditText userLatitudeTxt;
    private EditText userLongitudeTxt;

    float userLatitude;
    float userLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        compass = new Compass(this);
        compass.arrowView = (ImageView) findViewById(R.id.main_image_hands);

        userLatitudeTxt = (EditText) findViewById(R.id.Latitude);
        userLongitudeTxt = (EditText) findViewById(R.id.Longitude);

        findMyCurrentLocation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        compass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        compass.stop();
    }

    protected double bearing(double startLat, double startLng, double endLat, double endLng){
        double longitude1 = startLng;
        double longitude2 = endLng;
        double latitude1 = Math.toRadians(startLat);
        double latitude2 = Math.toRadians(endLat);
        double longDiff= Math.toRadians(longitude2-longitude1);
        double y= Math.sin(longDiff)*Math.cos(latitude2);
        double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);

        return (Math.toDegrees(Math.atan2(y, x))+360)%360;
    }

    public void findMyCurrentLocation(){
        gps = new GPSTracker(CompassActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            //gps.showSettingsAlert();
        }
    }



    public void onClick_startNavigate(View view) {
        userLatitude = Float.valueOf(userLatitudeTxt.getText().toString());
        userLongitude = Float.valueOf(userLongitudeTxt.getText().toString());

        compass = new Compass(this);

        compass.adjustArrow();
    }
}
