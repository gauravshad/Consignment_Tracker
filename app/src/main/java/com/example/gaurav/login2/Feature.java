package com.example.gaurav.login2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.widget.Toast;


public class Feature extends Activity {

    FeatureDataBaseAdapter featureDataBaseAdapter;
    protected String android_id;
    protected String device_id;
    protected String number;
    protected String simserial;
    protected String username;
    protected String advice;
    Button ok;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);


       //Toast.makeText(getApplicationContext(),"db"+featureDataBaseAdapter.getDatabaseInstance(), Toast.LENGTH_LONG).show();


        setContentView(R.layout.feature);

        username = getIntent().getStringExtra("USERNAME");
        advice = getIntent().getStringExtra("ADVICE");
        android_id = getIntent().getStringExtra("ANDROID");
        device_id = getIntent().getStringExtra("DEVICE");
        simserial = getIntent().getStringExtra("SIM");
        number = getIntent().getStringExtra("NUMBER");

       // featureDataBaseAdapter.insertEntry("username","advice","android_id","device_id","simserial","number");
        ok = (Button) findViewById(R.id.button1);

        ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }




}

