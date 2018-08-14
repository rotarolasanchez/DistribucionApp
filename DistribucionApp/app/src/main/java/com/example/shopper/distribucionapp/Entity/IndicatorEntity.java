package com.example.shopper.distribucionapp.Entity;

/**
 * Created by Shopper on 11/08/2018.
 */

public class IndicatorEntity {
    public String Company;
    public String Codigo;
    public String Fecha_Despacho;
    public String Codigo_Chofer;
    public String Entregado;
    public String Reprogramado;
    public String Anulado;
    public String Programado;
    public String Total;

    public IndicatorEntity()
    {

    }

    public IndicatorEntity(String company, String codigo, String fecha_Despacho, String codigo_Chofer, String entregado, String reprogramado, String anulado, String programado, String total) {
        Company = company;
        Codigo = codigo;
        Fecha_Despacho = fecha_Despacho;
        Codigo_Chofer = codigo_Chofer;
        Entregado = entregado;
        Reprogramado = reprogramado;
        Anulado = anulado;
        Programado = programado;
        Total = total;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getFecha_Despacho() {
        return Fecha_Despacho;
    }

    public void setFecha_Despacho(String fecha_Despacho) {
        Fecha_Despacho = fecha_Despacho;
    }

    public String getCodigo_Chofer() {
        return Codigo_Chofer;
    }

    public void setCodigo_Chofer(String codigo_Chofer) {
        Codigo_Chofer = codigo_Chofer;
    }

    public String getEntregado() {
        return Entregado;
    }

    public void setEntregado(String entregado) {
        Entregado = entregado;
    }

    public String getReprogramado() {
        return Reprogramado;
    }

    public void setReprogramado(String reprogramado) {
        Reprogramado = reprogramado;
    }

    public String getAnulado() {
        return Anulado;
    }

    public void setAnulado(String anulado) {
        Anulado = anulado;
    }

    public String getProgramado() {
        return Programado;
    }

    public void setProgramado(String programado) {
        Programado = programado;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
