package com.info.store;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Settingsact extends AppCompatActivity {
    EditText etusername,etpassword;
    SharedPreferences mysettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsact);
        etusername=(EditText)findViewById(R.id.etuser);
        etpassword=(EditText)findViewById(R.id.etpass);
        mysettings= getSharedPreferences("settings", 0);
        String getusername=mysettings.getString("username",""),getpassword=mysettings.getString("password","");
        etpassword.setText(getpassword);
        etusername.setText(getusername);
    }

    public void btnsavesettings(View view) {
        String username=etusername.getText().toString(),password=etpassword.getText().toString();
        mysettings= getSharedPreferences("settings", 0);

        if(username.equals("") || password.equals("")  ){
                    if(username.equals("")){
                      etusername.setError("empty field !! ");
                    }
                    if (password.equals("")) {
                        etpassword.setError(" empty field!! ");

                    }


        }else{

            SharedPreferences.Editor editor = mysettings.edit();
            editor.putString("username",username);
            editor.putString("password",password);
            editor.commit();
            finish();
        }

    }
}
