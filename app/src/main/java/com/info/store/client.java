package com.info.store;

import java.util.ArrayList;

/**
 * Created by adel on 15/04/2018.
 */

public class client {
    public String nameclient,phonenumber,email;
    public  int idclient;
    public client(int idclient,String nameclient,String phonenumber,String email){
        this.email=email;
        this.idclient=idclient;
        this.nameclient=nameclient;
        this.phonenumber=phonenumber;
    }

    public int getIdclient() {
        return idclient;
    }

    public String getEmail() {
        return email;
    }

    public String getNameclient() {
        return nameclient;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

}
