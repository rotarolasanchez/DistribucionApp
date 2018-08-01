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
 * Created by Shopper on 24/07/2018.
 */

public class ListaDespachoDao extends AppCompatActivity
{
    public  String  ActualizarLocalizacionCliente
            (
                    String company,

                    String latitud,
                    String longitud,
                    String shiptonum,
                    String custnum
            )
    {
        String resultado="";
        SoapObject rpc = new SoapObject("http://190.12.79.136/", "ActualizarLocalizacionCliente");
        rpc.addProperty("Company", company);
        rpc.addProperty("Latitude_c", latitud);
        rpc.addProperty("Longitude_c", longitud);
        rpc.addProperty("ShiptoNum", shiptonum);
        rpc.addProperty("CustNum", custnum);


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
            androidHttpTransport.call("http://190.12.79.136/ActualizarLocalizacionCliente",
                    envelope);
            // Respuesta del servicio web
            // Respuesta del servicio web
            resultado =  envelope.getResponse().toString();
            /*SoapObject result = (SoapObject) envelope.getResponse();
            int totalCount = result.getPropertyCount();
            if(totalCount>0)
            {
                resultado=1;
            }*/

        }
        catch (Exception e)
        {
            // TODO: handle exception
            System.out.println(e.getMessage());

        }
        return resultado;
    }
}
