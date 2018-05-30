package com.example.shopper.distribucionapp.Entity;

/**
 * Created by Shopper on 12/11/2017.
 */

public class ListaHojaDespachoEntity {
    public String Company;
    public String Salesrepcode;
    public String Date;
    public String Custnum;
    public String Shiptonum;
    public String SalesRepName;
    public String OrderDispatch;
    public String Custid;
    public String Name;
    public String Address_c;
    public String PhoneNum;
    public String Latitude_c;
    public String Longitude_c;
    public String FechaDespacho;
    public String ChkEntregado_c;
    public String ChkAnulado_c;
    public String ChkReprogramado_c;
    public String ChkPendiente_c;
    public String Ubigeo;
    public String State;
    public String LegalNumber;
    public String Packnum;

    public ListaHojaDespachoEntity()
    {

    }
    public ListaHojaDespachoEntity(String company, String salesrepcode, String date, String custnum, String shiptonum, String salesRepName, String orderDispatch, String custid, String name, String address_c, String phoneNum, String latitude_c, String longitude_c, String fechaDespacho, String chkEntregado_c, String chkAnulado_c, String chkReprogramado_c, String chkPendiente_c, String ubigeo, String state, String legalNumber, String packnum) {
        this.Company = company;
        this.Salesrepcode = salesrepcode;
        this.Date = date;
        this.Custnum = custnum;
        this.Shiptonum = shiptonum;
        this.SalesRepName = salesRepName;
        this.OrderDispatch = orderDispatch;
        this.Custid = custid;
        this.Name = name;
        this.Address_c = address_c;
        this.PhoneNum = phoneNum;
        this.Latitude_c = latitude_c;
        this.Longitude_c = longitude_c;
        this.FechaDespacho = fechaDespacho;
        this.ChkEntregado_c = chkEntregado_c;
        this.ChkAnulado_c = chkAnulado_c;
        this.ChkReprogramado_c = chkReprogramado_c;
        this.ChkPendiente_c = chkPendiente_c;
        this.Ubigeo = ubigeo;
        this.State = state;
        this.LegalNumber = legalNumber;
        this.Packnum = packnum;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getSalesrepcode() {
        return Salesrepcode;
    }

    public void setSalesrepcode(String salesrepcode) {
        Salesrepcode = salesrepcode;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCustnum() {
        return Custnum;
    }

    public void setCustnum(String custnum) {
        Custnum = custnum;
    }

    public String getShiptonum() {
        return Shiptonum;
    }

    public void setShiptonum(String shiptonum) {
        Shiptonum = shiptonum;
    }

    public String getSalesRepName() {
        return SalesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        SalesRepName = salesRepName;
    }

    public String getOrderDispatch() {
        return OrderDispatch;
    }

    public void setOrderDispatch(String orderDispatch) {
        OrderDispatch = orderDispatch;
    }

    public String getCustid() {
        return Custid;
    }

    public void setCustid(String custid) {
        Custid = custid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress_c() {
        return Address_c;
    }

    public void setAddress_c(String address_c) {
        Address_c = address_c;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getLatitude_c() {
        return Latitude_c;
    }

    public void setLatitude_c(String latitude_c) {
        Latitude_c = latitude_c;
    }

    public String getLongitude_c() {
        return Longitude_c;
    }

    public void setLongitude_c(String longitude_c) {
        Longitude_c = longitude_c;
    }

    public String getFechaDespacho() {
        return FechaDespacho;
    }

    public void setFechaDespacho(String fechaDespacho) {
        FechaDespacho = fechaDespacho;
    }

    public String getChkEntregado_c() {
        return ChkEntregado_c;
    }

    public void setChkEntregado_c(String chkEntregado_c) {
        this.ChkEntregado_c = chkEntregado_c;
    }

    public String getChkAnulado_c() {
        return ChkAnulado_c;
    }

    public void setChkAnulado_c(String chkAnulado_c) {
        this.ChkAnulado_c = chkAnulado_c;
    }

    public String getChkReprogramado_c() {
        return ChkReprogramado_c;
    }

    public void setChkReprogramado_c(String chkReprogramado_c) {
        this.ChkReprogramado_c = chkReprogramado_c;
    }

    public String getChkPendiente_c() {
        return ChkPendiente_c;
    }

    public void setChkPendiente_c(String chkPendiente_c) {
        this.ChkPendiente_c = chkPendiente_c;
    }

    public String getUbigeo() {
        return Ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        Ubigeo = ubigeo;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getLegalNumber() {
        return LegalNumber;
    }

    public void setLegalNumber(String legalNumber) {
        LegalNumber = legalNumber;
    }

    public String getPacknum() {
        return Packnum;
    }

    public void setPacknum(String packnum) {
        Packnum = packnum;
    }

    /*@Override
    public String toString(){
        return this.cliorilhd+this.clideslhd+this.cliestlhd;
    }*/

}
