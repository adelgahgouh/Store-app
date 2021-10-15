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

public class Clientact extends AppCompatActivity {
Toolbar toolbar;
    ListView lvclients;
    ArrayAdapter<client> arrayAdapter;
    database db;
    ArrayList<client> arrayList;
    TextView tvnbrclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientact);
        toolbar=(Toolbar)findViewById(R.id.toolbarclient);
        lvclients=(ListView) findViewById(R.id.lvclients);
        tvnbrclient=(TextView) findViewById(R.id.nbrclients);
        setSupportActionBar(toolbar);
        // back button
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setTitle("   CLIENTS");

arrayList=new ArrayList<client>();

        db=new  database(this);

      arrayList=db.getlistclients();
        tvnbrclient.setText("THERE ARE: "+arrayList.size()+" CLIENTS");
       arrayAdapter=new ArrayAdapter(getApplicationContext(), R.layout.listclientitem, arrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                // View g=convertView;

                if (view == null) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    view = layoutInflater.inflate(R.layout.listclientitem, null);
                }
                final client client=arrayList.get(position);
                TextView tvnameclient = (TextView) view.findViewById(R.id.tvnameclietlistitem);
                Button imgedit = (Button) view.findViewById(R.id.btneditclietlistitem);
                Button imgdelete = (Button) view.findViewById(R.id.btndeleteclietlistitem);
                ImageView imgclient = (ImageView) view.findViewById(R.id.imgclietlistitem);
                Glide.with(getApplicationContext()).load(R.drawable.client).into(imgclient);
                tvnameclient.setText(client.getNameclient());
                imgdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       db.delete_client(client.getIdclient());
                        arrayAdapter.remove(client);
                        arrayAdapter.notifyDataSetChanged();
                        tvnbrclient.setText("THERE ARE: "+arrayList.size()+" CLIENTS");
                    }
                });

                imgedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  db.delete_client(client.getIdclient());
                       // Toast.makeText(getApplicationContext(),"edit "+client.getIdclient(),Toast.LENGTH_SHORT).show();
                      Intent i=new Intent(getApplicationContext(),editclient.class);
                        i.putExtra("id",client.getIdclient());
                        i.putExtra("name",client.getNameclient());
                        i.putExtra("email",client.getEmail());
                        i.putExtra("phone",client.getPhonenumber());
                        startActivity(i);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

                return view;
            }
        };
lvclients.setAdapter(arrayAdapter);



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
                startActivity(new Intent(getApplicationContext(), addclient.class));
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
