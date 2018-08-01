package com.example.shopper.distribucionapp.Controller;

import android.Manifest;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.shopper.distribucionapp.Dao.TrackingDao;
import com.example.shopper.distribucionapp.View.TrackingView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

/**
 * Created by Shopper on 24/03/2018.
 */

public class GPSController2 extends Service implements LocationListener {

    private final Context context;
    boolean isGPSEnabled =false;
    boolean isNetworkEnabled =false;
    boolean canGetLocation = false;


    public Location location;
    protected LocationManager locationManager;

    @Override
    public void onCreate() {

        Log.d(TAG, "Servicio creado...");
        //googleApiClient =  new GoogleApiCliente();


        //location = gpsController.getLocation(location);
        //trackingView =  new TrackingView();
        //trackingDao= new TrackingDao();
        //latitude = location.getLatitude();
        //longitude = location.getLongitude();



    }


    public GPSController2(Context context){
        this.context=context;
    }

    //Create a GetLocation Method //
    public Location getLocation(Location location){
        try{

            //if(getLocation()!=null)
            //{
            //    getLocation().reset();
            //}

            //location.reset();
            location=null;
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isNetworkEnabled=locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);

            if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ){

                if(isGPSEnabled){
                    if(location==null){

                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

                        if(locationManager!=null){
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            //mapsActivity.tv1.setText("Buscando Coordenada GPS");
                        }
                    }
                }
                // if lcoation is not found from GPS than it will found from network //
               if(location==null){
                    if(isNetworkEnabled){

                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);
                        if(locationManager!=null){
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            //mapsActivity.tv2.setText("Buscando Coordenada de Red");
                        }

                    }
                }

            }

        }catch(Exception ex) {
            ex.printStackTrace();
            //Toast.makeText(getApplicationContext(), "GPS encontradp!!", Toast.LENGTH_SHORT).show();
            //mapsActivity.tv2.setText("RED ENCONTRADA");
        }
        return  location;

    }

    // followings are the default method if we imlement LocationListener //
    @Override
    public void onLocationChanged(Location location){
        getLocation(location);

    }
    @Override
    public void onStatusChanged(String Provider, int status, Bundle extras){

    }
    @Override
    public void onProviderEnabled(String Provider){

    }
    @Override
    public void onProviderDisabled(String Provider){

    }
    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }
    @Override
    public void onDestroy() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_NOT_STICKY;
    }

}
