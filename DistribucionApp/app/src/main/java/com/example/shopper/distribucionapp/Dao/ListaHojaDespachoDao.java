package com.example.shopper.distribucionapp.Dao;

import com.example.shopper.distribucionapp.Entity.ListaHojaDespachoEntity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shopper on 09/04/2018.
 */

public class ListaHojaDespachoDao {
    public List<ListaHojaDespachoEntity> Despachos;

    public List<ListaHojaDespachoEntity> obtenerDespachos(String vend, String fprog)
    {
        Despachos = new ArrayList<ListaHojaDespachoEntity>();
        Despachos.clear();
        SoapObject rpc = new SoapObject("http://190.12.79.136/", "ReprocesarDespachoOrdenado");
        rpc.addProperty("company", "c001");
        rpc.addProperty("vend", vend);
        rpc.addProperty("fprog", fprog);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        envelope.dotNet = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        HttpTransportSE androidHttpTransport = null;
        try
        {
                String conexion = "http://190.12.79.136/WebServiceDistribucionApp/WebServiceDistribucionApp.asmx";
                androidHttpTransport = new HttpTransportSE(conexion,1800000);
                androidHttpTransport.debug = true;
                androidHttpTransport.call("http://190.12.79.136/ReprocesarDespachoOrdenado",
                        envelope);
                // Respuesta del servicio web
                SoapObject result = (SoapObject) envelope.getResponse();
                int totalCount = 0;
                       totalCount=result.getPropertyCount();
                for (int detailCount = 0; detailCount < totalCount; detailCount++)
                {
                    SoapObject pojoSoap = (SoapObject) result
                            .getProperty(detailCount);
                    ListaHojaDespachoEntity Despacho = new ListaHojaDespachoEntity();
                    Despacho.Company = pojoSoap.getProperty(0).toString();
                    Despacho.Salesrepcode = pojoSoap.getProperty(1).toString();
                    Despacho.Date = pojoSoap.getProperty(2).toString();
                    Despacho.Custnum = pojoSoap.getProperty(3).toString();
                    Despacho.Shiptonum = pojoSoap.getProperty(4).toString();
                    Despacho.SalesRepName = pojoSoap.getProperty(5).toString();
                    Despacho.OrderDispatch = pojoSoap.getProperty(6).toString();
                    Despacho.Custid = pojoSoap.getProperty(7).toString();
                    Despacho.Name= pojoSoap.getProperty(8).toString();
                    Despacho.Address_c= pojoSoap.getProperty(9).toString();
                    Despacho.PhoneNum = pojoSoap.getProperty(10).toString();
                    Despacho.Latitude_c = pojoSoap.getProperty(11).toString();
                    Despacho.Longitude_c = pojoSoap.getProperty(12).toString();
                    Despacho.FechaDespacho = pojoSoap.getProperty(13).toString();
                    Despacho.ChkEntregado_c = pojoSoap.getProperty(14).toString();
                    Despacho.ChkAnulado_c = pojoSoap.getProperty(15).toString();
                    Despacho.ChkReprogramado_c = pojoSoap.getProperty(16).toString();
                    Despacho.ChkPendiente_c = pojoSoap.getProperty(17).toString();
                    Despacho.Ubigeo = pojoSoap.getProperty(18).toString();
                    Despacho.State = pojoSoap.getProperty(19).toString();
                    Despacho.LegalNumber = pojoSoap.getProperty(20).toString();
                    Despacho.Packnum = pojoSoap.getProperty(21).toString();
                    Despachos.add(Despacho);
                }

        }
        catch (Exception e)
        {
                // TODO: handle exception
                System.out.println(e.getMessage());

        }
        return Despachos;
    }

    public  String  EliminarDespachos
            (
                    String vend,
                    String fprog
            )
    {
        String resultado="";
        SoapObject rpc = new SoapObject("http://190.12.79.136/", "EliminarDespachoOrdenado");
        rpc.addProperty("company","C001");
        rpc.addProperty("vend", vend);
        rpc.addProperty("fprog", fprog);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        envelope.dotNet = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        HttpTransportSE androidHttpTransport = null;

        /*private final HttpTransportSE getHttpTransportSE() {
        HttpTransportSE androidHttpTransport = new HttpTransportSE(Proxy.NO_PROXY,MAIN_REQUEST_URL,60000);
        androidHttpTransport.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
        return ht;
         }*/
        try
        {
            String conexion = "http://190.12.79.136/WebServiceDistribucionApp/WebServiceDistribucionApp.asmx";
            androidHttpTransport = new HttpTransportSE(conexion,60000);
            androidHttpTransport.debug = true;
            androidHttpTransport.call("http://190.12.79.136/EliminarDespachoOrdenado",
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
