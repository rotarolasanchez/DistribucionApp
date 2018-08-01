package com.example.shopper.distribucionapp.View;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shopper.distribucionapp.Controller.GPSController;
import com.example.shopper.distribucionapp.Dao.DespachoEstadoDao;
import com.example.shopper.distribucionapp.Dao.ListaHojaDespachoDao;
import com.example.shopper.distribucionapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class DespachoEstadoView extends AppCompatActivity implements View.OnClickListener
{
    private GPSController gpsController;
    private Location mLocation;
    double latitude, longitude;
    Button btnguardarestado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despacho_estado);
        gpsController =  new GPSController(getApplicationContext());
        mLocation = gpsController.getLocation();
        latitude = gpsController.location.getLatitude();
        latitude = gpsController.location.getLatitude();
        longitude = mLocation.getLongitude();
        //btnguardarestado = (Button)findViewById(R.id.btnGuardarEstado);
        btnguardarestado.setOnClickListener(this);


    }

    public class HiloDespachoEstado extends AsyncTask<String,Void,Integer> {

        @Override
        protected Integer doInBackground(String... params) {

            try {
            DespachoEstadoDao DEstadoDAO = new DespachoEstadoDao();
            DEstadoDAO.ActualizarDespacho(Integer.parseInt(params[1]),Integer.parseInt(params[2]),Integer.parseInt(params[3])
                    ,Integer.parseInt(params[4]),
                    params[5],params[6],params[7],params[8],params[9],params[10]);
            }catch(Exception e)
            {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer s) {
            //resultado.setText(s);
            //super.onPostExecute(s);
    }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.btnGuardarEstado:

               // Obtenerdireccion();

               // break;
            default:

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_main, menu );
        return true;
    }


}
