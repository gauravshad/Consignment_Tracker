package com.example.gaurav.login2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;


public class Helpcarrier extends Activity {

    Button ok;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);

        setContentView(R.layout.helpcarrier);
        ok = (Button) findViewById(R.id.button1);

        ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }


}

