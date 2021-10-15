package com.info.store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by adel on 15/04/2018.
 */

public class database extends SQLiteOpenHelper {
    public static String dbname = "mystore";

    public static int version = 1;

    public database(Context context) {
        super(context, dbname, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE facture(idfacture INTEGER PRIMARY KEY AUTOINCREMENT ,fidclient TEXT,total TEXT ,date TEXT,path TEXT ) ;");
        db.execSQL("CREATE TABLE product(idproduct INTEGER PRIMARY KEY AUTOINCREMENT ,nameproduct TEXT ,price TEXT,quantite TEXT ); ");
        db.execSQL("CREATE TABLE client(idclient INTEGER PRIMARY KEY AUTOINCREMENT  ,nameclient TEXT,phonenumber TEXT,email TEXT  ) ;");
        db.execSQL("CREATE TABLE listproductoffacture(idlistproduct INTEGER PRIMARY KEY AUTOINCREMENT  ,lidfacture TEXT,lidproduct TEXT,quantite TEXT  ) ;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE facture");
        db.execSQL("DROP TABLE product");
        db.execSQL("DROP TABLE client");
        db.execSQL("DROP TABLE listproductoffacture");
        onCreate(db);
    }
//insertion
    public void insert_Facture( String total, String date, String fidclient,String path) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c2 = new ContentValues();
        c2.put("total", total);
        c2.put("date", date);
        c2.put("fidclient", fidclient);
        c2.put("path", path);
        db.insert("facture", null, c2);
    }
    public void insert_Product(String nameproduct, String price, String quantite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c2 = new ContentValues();
        c2.put("nameproduct", nameproduct);
        c2.put("price", price);
        c2.put("quantite", quantite);
        db.insert("product", null, c2);
    }
    public void insert_listproductoffacture(String lidfacture, String lidproduct, String quantite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c2 = new ContentValues();
        c2.put("lidfacture", lidfacture);
        c2.put("lidproduct", lidproduct);
        c2.put("quantite", quantite);
        db.insert("listproductoffacture", null, c2);
    }
    public void insert_client(String nameclient, String phonenumber, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c2 = new ContentValues();
        c2.put("nameclient", nameclient);
        c2.put("phonenumber", phonenumber);
        c2.put("email", email);
        db.insert("client", null, c2);
    }

  //update
    public void update_client(int idclient,String nameclient, String phonenumber, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c2 = new ContentValues();
        c2.put("nameclient", nameclient);
        c2.put("phonenumber", phonenumber);
        c2.put("email", email);
        db.update("client",c2,"idclient="+idclient,null);
    }
    public void update_Facture( int idfacture,String total, String date, String nameclient,String path) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c2 = new ContentValues();
        c2.put("total", total);
        c2.put("date", date);
        c2.put("path", path);
        c2.put("fidclient", nameclient);
        db.update("facture",c2,"idfacture="+idfacture,null);
    }
    public void update_Product(int idproduct,String nameproduct, String price, String quantite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c2 = new ContentValues();
        c2.put("nameproduct", nameproduct);
        c2.put("price", price);
        c2.put("quantite", quantite);
        db.update("product",c2,"idproduct="+idproduct,null);
    }
    //delete
    public void delete_product(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("product","idproduct="+id,null);
    }
    public void delete_facture(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("facture","idfacture="+id,null);
    }
    public void delete_client(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("client","idclient="+id,null);
    }
    public void delete_listproductoffacture(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("listproductoffacture","idlistproduct="+id,null);
    }
    //show
    public ArrayList<product> getlistproducts() {
        ArrayList array = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cu = db.rawQuery("SELECT * FROM product ", null);
        cu.moveToFirst();
        while (cu.isAfterLast() == false) {
            array.add(new product(cu.getInt(cu.getColumnIndex("idproduct")),cu.getString(cu.getColumnIndex("nameproduct")),cu.getString(cu.getColumnIndex("price")),cu.getString(cu.getColumnIndex("quantite"))));
            //array.add(cu.getString(cu.getColumnIndex("nameproduct")));
            cu.moveToNext();
        }
        return array;
    }
    public ArrayList<facture> getlistfacture() {
        ArrayList array = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cu = db.rawQuery("SELECT * FROM facture ", null);
        cu.moveToFirst();
        while (cu.isAfterLast() == false) {
            array.add(new facture(cu.getInt(cu.getColumnIndex("idfacture")),cu.getString(cu.getColumnIndex("total")),cu.getString(cu.getColumnIndex("nameclient")),cu.getString(cu.getColumnIndex("date")),cu.getString(cu.getColumnIndex("path"))));
            //array.add(cu.getString(cu.getColumnIndex("nameproduct")));
            cu.moveToNext();
        }
        return array;
    }
    public ArrayList<client> getlistclients() {
        ArrayList<client> array = new ArrayList<client>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cu = db.rawQuery("SELECT * FROM client ", null);
        cu.moveToFirst();
        while (cu.isAfterLast() == false) {
            array.add(new client(cu.getInt(cu.getColumnIndex("idclient")),cu.getString(cu.getColumnIndex("nameclient")),cu.getString(cu.getColumnIndex("phonenumber")),cu.getString(cu.getColumnIndex("email"))));
            cu.moveToNext();
        }
        return array;
    }
    public ArrayList<client> getlistproductoffacture() {
        ArrayList<client> array = new ArrayList<client>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cu = db.rawQuery("SELECT * FROM listproductoffacture ", null);
        cu.moveToFirst();
        while (cu.isAfterLast() == false) {
            array.add(new client(cu.getInt(cu.getColumnIndex("idlistproduct")),cu.getString(cu.getColumnIndex("lidproduct")),cu.getString(cu.getColumnIndex("lidfacture")),cu.getString(cu.getColumnIndex("quantite"))));
            cu.moveToNext();
        }
        return array;
    }


   }