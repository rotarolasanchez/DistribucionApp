package com.example.shopper.distribucionapp.Dao;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Shopper on 16/04/2018.
 */

public class LoginDao {

    public String ValidarUsuario(String user, String imei)
    {
        String resultado = "";
        SoapObject rpc = new SoapObject("http://190.12.79.136/", "LoginUsuarioImei");
        rpc.addProperty("cia", "01");
        rpc.addProperty("user", user);
        rpc.addProperty("imei", imei);
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
            androidHttpTransport.call("http://190.12.79.136/LoginUsuarioImei",
                    envelope);
            // Respuesta del servicio web
             resultado =  envelope.getResponse().toString();

        }
        catch (Exception e)
        {
            // TODO: handle exception
            System.out.println(e.getMessage());
            resultado=e.getMessage();

        }
        return resultado;
    }
}
