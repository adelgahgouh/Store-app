package com.info.store;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class editclient extends AppCompatActivity {
    EditText etnameclient,etemailclient,etphonenumberclient;
    String nclient,emclient,phclient;
    int idclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editclient);
        etnameclient=(EditText)findViewById(R.id.etnameclientedit);
        etphonenumberclient=(EditText)findViewById(R.id.etphoneclientedit);
        etemailclient=(EditText)findViewById(R.id.etemailclientedit);
    idclient=getIntent().getExtras().getInt("id");
    nclient=getIntent().getExtras().getString("name");
    emclient=getIntent().getExtras().getString("email");
    phclient=getIntent().getExtras().getString("phone");
        etnameclient.setText(nclient);
        etemailclient.setText(emclient);
        etphonenumberclient.setText(phclient);

    }

    public void btnsaveeditclient(View view) {
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
            db.update_client(idclient,name,phone,email);
            startActivity(new Intent(getApplicationContext(), Clientact.class));
            finish();
        }
    }
}
