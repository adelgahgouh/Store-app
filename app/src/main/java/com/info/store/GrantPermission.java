package com.info.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GrantPermission extends AppCompatActivity {
    public static final int MULTIPLE_PERMISSIONS = 1;
    String[] permissions= new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
            };
    private PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grant_permission);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);

        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
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
    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS:{
                if (grantResults.length > 0) {
                    String permissionsDenied = "";
                    for (String per : permissionsList) {
                        if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                            permissionsDenied += "\n" + per;

                        }

                    }
                    // Show permissionsDenied
                }
                return;
            }
        }
    }

    public void btngrant_permission(View view) {
        if(checkPermissions()){

            launchHomeScreen();
            creatDirectory();
        }
    }
    void creatDirectory() {
        File DirectoryImg,DirectoryETmp,DirectoryTmp,Temp_view_img_file,Temp_Output_img_file,Temp_Choosen_img_file;


        File sdCard = Environment.getExternalStorageDirectory();

        File Directory = new File(sdCard.getPath() + "/Filter Me");
        DirectoryImg = new File(sdCard.getPath() + "/Filter Me/Images");
        DirectoryTmp = new File(sdCard.getPath() + "/Filter Me/Tmp");
        DirectoryETmp = new File(sdCard.getPath() + "/Filter Me/ETmp");




        if (!Directory.exists())
            Directory.mkdir();
        if (!DirectoryImg.exists())
            DirectoryImg.mkdir();
        if (!DirectoryTmp.exists())
            DirectoryTmp.mkdir();
        if (!DirectoryETmp.exists())
            DirectoryETmp.mkdir();
    }

}
