package com.info.store;

/**
 * Created by adel on 18/04/2018.
 */

public class cproduct {


        public int quantitie, idproduct;

        public int getQuantitie() {
            return quantitie;
        }

        public cproduct(int idproduct, int quntite) {
            this.idproduct = idproduct;
            this.quantitie = quntite;

        }

        public void setQuantitie(int quantitie) {
            this.quantitie = quantitie;
        }


        public void setIdproduct(int idproduct) {
            this.idproduct = idproduct;
        }

        public int getIdproduct() {
            return idproduct;
        }




}
