package com.info.store;

/**
 * Created by adel on 18/04/2018.
 */

public class listproductoffacture {
    public String idproduct,idfacture,quantite;
    int idlistproduct;
    public listproductoffacture(int idlistproduct,String idproduct,String idfacture,String quantite){
        this.idlistproduct=idlistproduct;
        this.idfacture=idfacture;
        this.idproduct=idproduct;
        this.quantite=quantite;
    }

    public int getIdlistproduct() {
        return idlistproduct;
    }

    public String getIdfacture() {
        return idfacture;
    }

    public String getIdproduct() {
        return idproduct;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public void setIdfacture(String idfacture) {
        this.idfacture = idfacture;
    }

    public void setIdlistproduct(int idlistproduct) {
        this.idlistproduct = idlistproduct;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }
}
