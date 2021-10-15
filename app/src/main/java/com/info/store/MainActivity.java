package com.info.store;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CardView btnclient, btnfacture, btnproducts, btnsettings;
    Button btnabout;
    final int MULTIPLE_PERMISSIONS = 10;
    String[] permissions= new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnclient = (CardView) findViewById(R.id.clientbtn);
        btnfacture = (CardView) findViewById(R.id.listfacturesbtn);
        btnproducts = (CardView) findViewById(R.id.listproductbtn);
        btnsettings = (CardView) findViewById(R.id.settingbtn);
        btnabout = (Button) findViewById(R.id.btnabout);
        checkPermissions();
        createfolder();
        btnclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Clientact.class);
                startActivity(i);
            }
        });
        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Aboutact.class);
                startActivity(i);
            }
        });
        btnfacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Facturact.class);
                startActivity(i);
            }
        });

        btnproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Productact.class);
                startActivity(i);
            }
        });
        btnsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Settingsact.class);
                startActivity(i);
            }
        });

    }
    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(getApplicationContext(),p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                } else {
                }
                return;
            }

        }
    }
    public void createfolder() {
        // File myDirectory = new File(Environment.getExternalStorageDirectory(), "teacher result");

        File myDirectory = new File(Environment.getExternalStorageDirectory() ,
                "my store pdf");
        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        }

    }

}

