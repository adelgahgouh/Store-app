package com.info.store;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Facturact extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvfacures;
    ArrayAdapter<facture> arrayAdapter;
    database db;
    ArrayList<facture> arrayList;
    TextView tvnbrfacutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturact);
        toolbar=(Toolbar)findViewById(R.id.toolbarfacture);
        lvfacures=(ListView) findViewById(R.id.lvfacture);
        tvnbrfacutes=(TextView) findViewById(R.id.tvnbfactures);
        setSupportActionBar(toolbar);
        setTitle("   FACTURES");
        arrayList=new ArrayList<facture>();
        db=new  database(this);
        arrayList=db.getlistfacture();
        tvnbrfacutes.setText("THERE ARE: "+arrayList.size()+" FACTURES");
        arrayAdapter=new ArrayAdapter(getApplicationContext(), R.layout.listproductitem, arrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                // View g=convertView;

                if (view == null) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    view = layoutInflater.inflate(R.layout.listproductitem, null);
                }
                final facture facture=arrayList.get(position);
                TextView tvnameclient = (TextView) view.findViewById(R.id.tvnameclientfacture);
                TextView tvtotal = (TextView) view.findViewById(R.id.tvtotalpricefactureact);
                TextView tvdate = (TextView) view.findViewById(R.id.datefacture);
                Button imgedit = (Button) view.findViewById(R.id.btneditfacture);
                Button imgdelete = (Button) view.findViewById(R.id.btndeletefacture);
                ImageView imgproduct= (ImageView) view.findViewById(R.id.imgfactureact);
                Glide.with(getApplicationContext()).load(R.drawable.factur).into(imgproduct);
                tvnameclient.setText(facture.getNameclient());
                tvtotal.setText(facture.getPricefacture()+" $");
                tvdate.setText(facture.getDatefacture());
                imgdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.delete_facture(facture.getIdfacture());
                        //delete all product of id facture
                        arrayAdapter.remove(facture);
                        arrayAdapter.notifyDataSetChanged();
                        tvnbrfacutes.setText("THERE ARE: "+arrayList.size()+" FACTURES");
                    }
                });

                imgedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    //    Intent i=new Intent(getApplicationContext(),editproduct.class);
                      /*  i.putExtra("id",facture.getIdproduct());
                        i.putExtra("name",facture.getNameproduct());
                        i.putExtra("price",facture.getPrice());
                        i.putExtra("quantite",facture.getQuantity());
                        Toast.makeText(getApplicationContext(),"quantite : "+facture.getQuantity(),Toast.LENGTH_SHORT).show();
                        */
                     // startActivity(i);
                        arrayAdapter.notifyDataSetChanged();

                    }
                });

                return view;
            }
        };
        lvfacures.setAdapter(arrayAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.add): {
                startActivity(new Intent(getApplicationContext(), addfacture.class));
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
