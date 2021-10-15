package com.info.store;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class editproduct extends AppCompatActivity {
    EditText etnameproduct,etpriceproduct,etquntiteproduct;
    String nproduct,prproduct,qproduct;
    int idproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editproduct);
        etnameproduct=(EditText)findViewById(R.id.etnameproductedit);
        etpriceproduct=(EditText)findViewById(R.id.etpriceproductedit);
        etquntiteproduct=(EditText)findViewById(R.id.etquantiteproductedit);
        idproduct=getIntent().getExtras().getInt("id");
        prproduct=getIntent().getExtras().getString("price");
        nproduct=getIntent().getExtras().getString("name");
        qproduct=getIntent().getExtras().getString("quantite");
        etnameproduct.setText(nproduct);
        etpriceproduct.setText(prproduct);
        etquntiteproduct.setText(qproduct);
    }

    public void btnsaveeditproduct(View view) {
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
            db.update_Product(idproduct,name,price,quntite);
            startActivity(new Intent(getApplicationContext(), Productact.class));
            finish();
        }
    }
}
