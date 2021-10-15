package com.info.store;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addproduct extends AppCompatActivity {
    EditText etnameproduct,etpriceproduct,etquntiteproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        etnameproduct=(EditText)findViewById(R.id.etnameproductadd);
        etpriceproduct=(EditText)findViewById(R.id.etpriceproductadd);
        etquntiteproduct=(EditText)findViewById(R.id.etquantiteproductadd);
    }

    public void btnsaveaddproduct(View view) {
        String name=etnameproduct.getText().toString(),price=etpriceproduct.getText().toString(),quntite=etquntiteproduct.getText().toString();
        if(name.equals("") || price.equals("") || quntite.equals("")){
            if(name.equals("")){
                etnameproduct.setError("empty fields !!");
            }
            if(quntite.equals("")){
                etquntiteproduct.setError("empty fields !!");
            }
            if(price.equals("")){
                etpriceproduct.setError("empty fields !!");
            }

        }else{
            database db=new database(getApplicationContext());
            db.insert_Product(name,price,quntite);
            startActivity(new Intent(getApplicationContext(), Productact.class));
            finish();
        }
    }
}
