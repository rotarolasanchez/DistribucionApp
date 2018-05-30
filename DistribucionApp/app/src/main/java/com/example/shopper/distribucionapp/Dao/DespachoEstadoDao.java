package com.example.shopper.distribucionapp.Dao;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopper.distribucionapp.Controller.DespachoEstadoDialog;
import com.example.shopper.distribucionapp.Controller.GPSController;
import com.example.shopper.distribucionapp.Entity.ListaHojaDespachoEntity;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Shopper on 29/03/2018.
 */

public class DespachoEstadoDao extends AppCompatActivity
{
    private Context context;
    TextView resultado,resultado2;
    private GPSController gpsController;
    private Location mLocation;
    double latitude=0, longitude=0;


    public  int  ActualizarDespacho
            (
            int entregado,
            int reprogramado,
            int anulado,
            int pendiente,
            String latitud,
            String longitud,
            String orderdispatch,
            String vend,
            String fprog,
            String company
            )
    {
        int resultado=0;
        SoapObject rpc = new SoapObject("http://190.12.79.136/", "ActualizarDespachoOrdenado");
        rpc.addProperty("entregado", entregado);
        rpc.addProperty("reprogramado", reprogramado);
        rpc.addProperty("anulado", anulado);
        rpc.addProperty("pendiente", pendiente);
        rpc.addProperty("latitude_c", latitud);
        rpc.addProperty("longitude_c", longitud);
        rpc.addProperty("orderdispatch", orderdispatch);
        rpc.addProperty("vend", vend);
        rpc.addProperty("fechadespacho", fprog);
        rpc.addProperty("company", company);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        envelope.dotNet = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        HttpTransportSE androidHttpTransport = null;
        try
        {
            String conexion = "http://190.12.79.136/WebServiceDistribucionApp/WebServiceDistribucionApp.asmx";
            androidHttpTransport = new HttpTransportSE(conexion);
            androidHttpTransport.debug = true;
            androidHttpTransport.call("http://190.12.79.136/ActualizarDespachoOrdenado",
                    envelope);
            // Respuesta del servicio web
            SoapObject result = (SoapObject) envelope.getResponse();
            int totalCount = result.getPropertyCount();
            if(totalCount>0)
            {
                resultado=1;
            }

        }
        catch (Exception e)
        {
            // TODO: handle exception
            System.out.println(e.getMessage());

        }
        return resultado;
    }

}
