package com.example.shopper.distribucionapp.Dao;

import android.content.Context;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shopper.distribucionapp.Controller.GPSController;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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


    public  String  ActualizarDespacho
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
        String resultado="";
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
            // Respuesta del servicio web
            resultado =  envelope.getResponse().toString();
            /*SoapObject result = (SoapObject) envelope.getResponse();
            int totalCount = result.getPropertyCount();
            if(totalCount>0)
            {
                resultado=1;
            }
            */
        }
        catch (Exception e)
        {
            // TODO: handle exception
            System.out.println(e.getMessage());

        }
        return resultado;
    }

}
