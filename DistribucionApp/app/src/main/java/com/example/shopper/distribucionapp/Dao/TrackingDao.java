package com.example.shopper.distribucionapp.Dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Shopper on 10/07/2018.
 */

public class TrackingDao {

    public  String  InsertarTracking
            (
                    String Company,
                    String SalesRepCode,
                    String Latitude_c,
                    String Longitude_c,
                    String Date,
                    String Time
            )
    {
        String resultado="";
        SoapObject rpc = new SoapObject("http://190.12.79.136/", "InsertarTracking");
        rpc.addProperty("Company", Company);
        rpc.addProperty("SalesRepCode", SalesRepCode);
        rpc.addProperty("Latitude_c", Latitude_c);
        rpc.addProperty("Longitude_c", Longitude_c);
        rpc.addProperty("Date", Date);
        rpc.addProperty("Time", Time);
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
            androidHttpTransport.call("http://190.12.79.136/InsertarTracking",
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
