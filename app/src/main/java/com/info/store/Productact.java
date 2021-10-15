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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Productact extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvcproducts;
    ArrayAdapter<product> arrayAdapter;
    database db;
    ArrayList<product> arrayList;
    TextView tvnbrproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productact);
        toolbar=(Toolbar)findViewById(R.id.toolbarproduct);
        lvcproducts=(ListView) findViewById(R.id.lvproducts);
        tvnbrproduct=(TextView) findViewById(R.id.nbrproducts);
        setSupportActionBar(toolbar);
        setTitle("   PRODUCTS");
        arrayList=new ArrayList<product>();
        db=new  database(this);
        arrayList=db.getlistproducts();
        tvnbrproduct.setText("THERE ARE: "+arrayList.size()+" PRODUCTS");
        arrayAdapter=new ArrayAdapter(getApplicationContext(), R.layout.listproductitem, arrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                // View g=convertView;

                if (view == null) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    view = layoutInflater.inflate(R.layout.listproductitem, null);
                }
                final product product=arrayList.get(position);
                TextView tvnameproduct = (TextView) view.findViewById(R.id.tvnameproductlistitem);
                TextView tvpriceproduct = (TextView) view.findViewById(R.id.tvpriceproductlistitem);
                TextView tvquntiteproduct = (TextView) view.findViewById(R.id.tvquantiteproductlistitem);
                Button imgedit = (Button) view.findViewById(R.id.btneditproductlistitem);
                Button imgdelete = (Button) view.findViewById(R.id.btndeleteproductlistitem);
                ImageView  imgproduct= (ImageView) view.findViewById(R.id.imgprouctlistitem);
                Glide.with(getApplicationContext()).load(R.drawable.product).into(imgproduct);
                tvnameproduct.setText(product.getNameproduct());
                tvpriceproduct.setText(product.getPrice()+" $");
                tvquntiteproduct.setText(product.getQuantity()+" unit");
                imgdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.delete_product(product.getIdproduct());
                        arrayAdapter.remove(product);
                        arrayAdapter.notifyDataSetChanged();
                        tvnbrproduct.setText("THERE ARE: "+arrayList.size()+" PRODUCTS");
                    }
                });

                imgedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(getApplicationContext(),editproduct.class);
                        i.putExtra("id",product.getIdproduct());
                        i.putExtra("name",product.getNameproduct());
                        i.putExtra("price",product.getPrice());
                        i.putExtra("quantite",product.getQuantity());
                        Toast.makeText(getApplicationContext(),"quantite : "+product.getQuantity(),Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        arrayAdapter.notifyDataSetChanged();

                    }
                });

                return view;
            }
        };
        lvcproducts.setAdapter(arrayAdapter);
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
                startActivity(new Intent(getApplicationContext(), addproduct.class));
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
