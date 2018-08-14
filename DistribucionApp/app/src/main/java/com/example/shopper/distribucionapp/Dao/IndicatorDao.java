package com.example.shopper.distribucionapp.Dao;

import com.example.shopper.distribucionapp.Entity.IndicatorEntity;
import com.example.shopper.distribucionapp.Entity.ListaHojaDespachoEntity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shopper on 11/08/2018.
 */

public class IndicatorDao {
    public List<IndicatorEntity> Eindicator;

    public List<IndicatorEntity> obtenerIndicadorNC(String chofer, String fechainicio,String fechafin)
    {
        //indicator.clear();
        Eindicator= new ArrayList<IndicatorEntity>();
        Eindicator.clear();
        SoapObject rpc = new SoapObject("http://190.12.79.136/", "ObtenerIndicadorNivelCumplimiento");
        rpc.addProperty("company", "C001");
        rpc.addProperty("chofer", chofer);
        rpc.addProperty("fechainicio", fechainicio);
        rpc.addProperty("fechafin", fechafin);
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
            androidHttpTransport.call("http://190.12.79.136/ObtenerIndicadorNivelCumplimiento",
                    envelope);
            // Respuesta del servicio web
            SoapObject result = (SoapObject) envelope.getResponse();
            int totalCount = 0;
            totalCount=result.getPropertyCount();
            for (int detailCount = 0; detailCount < totalCount; detailCount++)
            {
                SoapObject pojoSoap = (SoapObject) result
                        .getProperty(detailCount);
                IndicatorEntity Indicator = new IndicatorEntity();
                Indicator.Company = pojoSoap.getProperty(0).toString();
                Indicator.Codigo = pojoSoap.getProperty(1).toString();
                Indicator.Fecha_Despacho = pojoSoap.getProperty(2).toString();
                Indicator.Codigo_Chofer = pojoSoap.getProperty(3).toString();
                Indicator.Entregado = pojoSoap.getProperty(4).toString();
                Indicator.Reprogramado = pojoSoap.getProperty(5).toString();
                Indicator.Anulado = pojoSoap.getProperty(6).toString();
                Indicator.Programado = pojoSoap.getProperty(7).toString();
                Indicator.Total= pojoSoap.getProperty(8).toString();
                Eindicator.add(Indicator);
            }

        }
        catch (Exception e)
        {
            // TODO: handle exception
            System.out.println(e.getMessage());

        }
        return Eindicator;
    }
}
