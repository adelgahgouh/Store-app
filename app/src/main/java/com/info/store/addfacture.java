package com.info.store;



import android.os.AsyncTask;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class addfacture extends AppCompatActivity {
    Spinner spclient;
    TextView tvtotoal;
    ListView lvproducts;
    database db;
    ArrayList<client> listclient;
    ArrayList<product> listproduct;
    ArrayAdapter<client> adapterclient;
    ArrayAdapter<product> adapterproduct;
    ArrayList<cproduct> currentproduct;
    public float price = 0;
    boolean d = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfacture);
        spclient = (Spinner) findViewById(R.id.spinnerclientadd);
        lvproducts = (ListView) findViewById(R.id.lvaddfacture);
        tvtotoal = (TextView) findViewById(R.id.tvtotalpriceaddfacture);
        db = new database(this);
        listproduct = new ArrayList();
        currentproduct = new ArrayList();
        listclient = new ArrayList<>();
        listclient = db.getlistclients();
        listproduct = db.getlistproducts();
       /* ArrayList<String> nameclient = new ArrayList<>();
        for (int i = 0; i < listclient.size(); i++) {
            nameclient.add(listclient.get(i).getNameclient());
        }*/
        adapterclient = new ArrayAdapter<client>(getApplicationContext(), R.layout.itemspinner, listclient){
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                return getCustomView(position, convertView, parent);
            }

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                return getCustomView(position, convertView, parent);
            }
            private View getCustomView(final int position, View convertView, ViewGroup parent) {
                View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemspinner, parent, false);
                final TextView label=(TextView)row.findViewById(R.id.tvspinner);
                label.setText(listclient.get(position).getNameclient());
                return row;
            }

        };

        spclient.setAdapter(adapterclient);

        //product

        adapterproduct = new ArrayAdapter<product>(getApplicationContext(), R.layout.listfactureitemproduct, listproduct) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                // View g=convertView;

                if (view == null) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    view = layoutInflater.inflate(R.layout.listfactureitemproduct, null);
                }

                final product product = listproduct.get(position);
                TextView tvnameproduct = (TextView) view.findViewById(R.id.tvnameproductfactlistitem);
                TextView tvpriceproduct = (TextView) view.findViewById(R.id.tvpriceproductfactlistitem);
                TextView tvquntiteproduct = (TextView) view.findViewById(R.id.tvquntitefact);

                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkboxfact);
                EditText quantitecurrent = (EditText) view.findViewById(R.id.quntiteproductfact);

                ImageView imgproduct = (ImageView) view.findViewById(R.id.imgfactlistitem);
                Glide.with(getApplicationContext()).load(R.drawable.product).into(imgproduct);
                tvnameproduct.setText(product.getNameproduct());
                tvpriceproduct.setText(product.getPrice() + " $");
                tvquntiteproduct.setText("/" + product.getQuantity());
                int cquntite = Integer.valueOf(quantitecurrent.getText().toString());
                final int c = cquntite;


                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        product.setSelected(buttonView.isChecked());
                        //Toast.makeText(getApplicationContext(),""+product.isSelected(),Toast.LENGTH_SHORT).show();

                        if (product.isSelected()) {
                            float pricenow = Float.valueOf(product.getPrice()) * c;
                            price += pricenow;
                            currentproduct.add(new cproduct(product.getIdproduct(), c));
                        } else {
                            if (price > 0) {

                                int quantite=getquantite(product.getIdproduct());
                                float pricenow = Float.valueOf(product.getPrice())*quantite;
                                        price -= pricenow;
currentproduct.remove(getcproduct(product.getIdproduct()));
                            }
                        }
                        adapterproduct.notifyDataSetChanged();

                    }
                });

                int quntite = Integer.valueOf(product.getQuantity());
                if (cquntite > quntite) {
                    quantitecurrent.setText("0");
                }


                tvtotoal.setText(price + "$");
                return view;
            }
        };

        lvproducts.setAdapter(adapterproduct);


    }

    public void btnaddfacture(View view) {

        if (price > 0) {


//get all selectable product;

            //getclient id
            String path= Environment.getExternalStorageDirectory()+"/my store pdf/pdf";

            int clientidpos=spclient.getSelectedItemPosition();
           db.insert_Facture(""+price,getcurrentdate(),""+listclient.get(clientidpos).getIdclient(),path);
        } else {
            Toast.makeText(getApplicationContext(), "you have'nt any product", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList getsetctableproducts() {
        ArrayList products = new ArrayList();
        for (int i = 0; i < listproduct.size(); i++) {
            if (listproduct.get(i).isSelected()) {
                products.add(listproduct.get(i));
            }
        }
        return products;
    }

    void createpdf() {

    }

    String getcurrentdate() {
        String result = "";
        result = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
        return result;
    }
    int getquantite(int id){
       int r=0;
        for(int i=0;i<currentproduct.size();i++){
            if(currentproduct.get(i).getIdproduct()==id){
                r=currentproduct.get(i).getQuantitie();
        }

    }
        return r;
    }
    int getcproduct(int id){
        int r=0;
        for(int i=0;i<currentproduct.size();i++){
            if(currentproduct.get(i).getIdproduct()==id){
                r=i;
            }
    }
    return r;}
    public class taskpdf extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }
}
