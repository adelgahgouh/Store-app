package com.info.store;

/**
 * Created by adel on 15/04/2018.
 */

public class facture {
    public  int idfacture;
    public String pricefacture;
    public String nameclient,datefacture,path;
    public  facture(int idfacture,String pricefacture,String nameclient,String datefacture,String path){
    this.idfacture=idfacture;
    this.nameclient=nameclient;
    this.pricefacture=pricefacture;
        this.path=path;
        this.datefacture=datefacture;
    }
    public String getNameclient() {
        return nameclient;
    }

    public String getPath() {
        return path;
    }

    public void setIdfacture(int idfacture) {
        this.idfacture = idfacture;
    }

    public void setDatefacture(String datefacture) {
        this.datefacture = datefacture;
    }

    public void setNameclient(String nameclient) {
        this.nameclient = nameclient;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPricefacture(String pricefacture) {
        this.pricefacture = pricefacture;
    }

    public String getDatefacture() {
        return datefacture;
    }

    public String getPricefacture() {
        return pricefacture;
    }

    public int getIdfacture() {
        return idfacture;
    }
}
