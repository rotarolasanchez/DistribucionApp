package com.example.shopper.distribucionapp.Controller;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.shopper.distribucionapp.Dao.TrackingDao;
import com.example.shopper.distribucionapp.Entity.DespachoEstadoEntity;
import com.example.shopper.distribucionapp.Entity.LoginEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Shopper on 07/07/2018.
 */

public class ServiceGPSController extends Service {
    private static final String TAG = ServiceGPSController.class.getSimpleName();
    Context context;
    TimerTask timerTask;
    Location location;
    double latitude = 0, longitude = 0;
    private GPSController2 gpsController;
    TrackingController trackingController;
    LoginEntity dSesion = new LoginEntity();
    DespachoEstadoEntity Destado = new DespachoEstadoEntity();
    TrackingDao trackingDao;




    public ServiceGPSController() {

    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Log.d(TAG, "Servicio creado...");
        //googleApiClient =  new GoogleApiCliente();


        //location = gpsController.getLocation(location);
        trackingController =  new TrackingController();
        trackingDao= new TrackingDao();
        //latitude = location.getLatitude();
        //longitude = location.getLongitude();
        gpsController = new GPSController2(getApplicationContext());
        location = gpsController.getLocation(location);



    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "Servicio iniciado...");
        Toast.makeText(this,"Servicio iniciado...",Toast.LENGTH_LONG).show();


        final ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        final ActivityManager activityManager =
                (ActivityManager) getSystemService(ACTIVITY_SERVICE);


        Timer timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {



                //gpsController = new GPSController(getApplicationContext());
                //location = gpsController.getLocation();
                //latitude = 0;
                //longitude = 0;
                latitude = 0;
                longitude = 0;
                location = gpsController.getLocation(location);
                latitude=location.getLatitude();
                longitude=location.getLongitude();
                String availMem = String.valueOf(latitude) + String.valueOf(longitude);
                //writeActualLocation();

                //memoryInfo.availMem / 1048576 + "MB";

                Log.d(TAG, availMem);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                final Date date = new Date();

                final String fecha = dateFormat.format(date);

                Calendar calendario = Calendar.getInstance();
                calendario = new GregorianCalendar();
                int hora=0, minutos=0, segundos=0;
                hora =calendario.get(Calendar.HOUR_OF_DAY);
                minutos = calendario.get(Calendar.MINUTE);
                segundos = calendario.get(Calendar.SECOND);

                final String time = hora+":"+minutos+":"+segundos;
                activityManager.getMemoryInfo(memoryInfo);

                new Thread(new Runnable() {
                    public void run() {

                        trackingDao= new TrackingDao();

                        trackingDao.InsertarTracking(
                                Destado.Company.toString(),
                                dSesion.usuarioSesion.toString(),
                                String.valueOf(latitude),
                                String.valueOf(longitude),
                                fecha,
                                time
                        );
                    }

                    }).start();

            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 100000);

        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {

        timerTask.cancel();
        Intent localIntent = new Intent(Constants.ACTION_MEMORY_EXIT);

        // Emitir el intent a la actividad
        LocalBroadcastManager.
                getInstance(ServiceGPSController.this).sendBroadcast(localIntent);
        Log.d(TAG, "Servicio destruido...");
    }



}
