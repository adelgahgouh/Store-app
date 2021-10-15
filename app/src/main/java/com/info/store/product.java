package com.info.store;

/**
 * Created by adel on 15/04/2018.
 */

public class product {
    public int idproduct;
    public String nameproduct;
    public String price;
    public String quantity;
    public boolean selected=false;
    public product(int idproduct,String nameproduct,String price,String quantity){
        this.price=price;
        this.nameproduct=nameproduct;
        this.quantity=quantity;
        this.idproduct=idproduct;
    }

    public String getPrice() {
        return price;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public String getQuantity() {
        return quantity;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
