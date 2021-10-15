package com.info.store;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addclient extends AppCompatActivity {
EditText etnameclient,etemailclient,etphonenumberclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclient);
        etnameclient=(EditText)findViewById(R.id.etnameclientadd);
        etphonenumberclient=(EditText)findViewById(R.id.etphoneclientadd);
        etemailclient=(EditText)findViewById(R.id.etemailclientadd);
    }

    public void btnsaveaddclient(View view) {
        String name=etnameclient.getText().toString(),email=etemailclient.getText().toString(),phone=etphonenumberclient.getText().toString();
        if(name.equals("") || email.equals("") || phone.equals("")){
            if(name.equals("")){
                etnameclient.setError("empty fields !!");
            }
            if(phone.equals("")){
                etphonenumberclient.setError("empty fields !!");
            }
            if(email.equals("")){
                etemailclient.setError("empty fields !!");
            }

        }else{
            database db=new database(getApplicationContext());
            db.insert_client(name,phone,email);
            startActivity(new Intent(getApplicationContext(), Clientact.class));
            finish();
        }
    }
}
