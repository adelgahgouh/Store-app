package com.info.store;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
EditText etusername,etpassword;
    SharedPreferences mysettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etusername=(EditText)findViewById(R.id.etuserlogin);
        etpassword=(EditText)findViewById(R.id.etpasslogin);
        defaultsetting();
    }

    public void btnlogin(View view) {
        String username=etusername.getText().toString(),password=etpassword.getText().toString();
        mysettings= getSharedPreferences("settings", 0);

        String getusername=mysettings.getString("username",""),getpassword=mysettings.getString("password","");
       if(password.equals("") || username.equals("")){
                    if(password.equals("")){
                    etpassword.setError("empty field !! ");
                    }
                    if(username.equals("")){
                        etusername.setError("empty field  !! ");
                    }
       }else {
                   if (username.equals(getusername) && password.equals(getpassword)) {
                       Intent i = new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(i);
                       finish();
                   } else {
                       if (!username.equals(getusername)){
                           etusername.setError("wrong username !! ");
                       }
                       if (!password.equals(getpassword)){
                           etpassword.setError("wrong password !! ");
                       }

                         }

             }
    }
    void defaultsetting(){

        SharedPreferences  preferences = getSharedPreferences("settings", 0);
        String value = preferences.getString("username","");
        if (value .equals("")) {
            SharedPreferences.Editor ed=preferences.edit();


            ed.putString("username", "admin");

            ed.putString("password", "123456");

            ed.commit();

            //Set some default shared pref
        }


    }
}
