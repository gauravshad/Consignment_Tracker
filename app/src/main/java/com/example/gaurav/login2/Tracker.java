package com.example.gaurav.login2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
//import android.app.ActionBar;
import android.app.Dialog;
import android.app.PendingIntent;
//import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class Tracker extends FragmentActivity {

    GoogleMap googleMap;
    LocationManager locationManager;
    PendingIntent pendingIntent;
    SharedPreferences sharedPreferences;
    SharedPreferences sp;
    Button ok;
    int fl=0;
    Button mBtnFind;
    EditText etPlace;
    //int locationCount = 0;
    LocationDataBaseAdapter locationDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        //ActionBar actionBar = getActionBar();
        //actionBar.setLogo(R.drawable.icon);
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);
        /***********************************************************/
        //sp = getSharedPreferences("flag", 0);
        //fl = sp.getInt("fl", 0);

        setContentView(R.layout.tracker);

        locationDataBaseAdapter=new LocationDataBaseAdapter(this);
        locationDataBaseAdapter=locationDataBaseAdapter.open();

       /* if (fl == 0) {
            fl = 1;
            SharedPreferences.Editor edit = sp.edit();
            edit.putInt("fl", fl);
            edit.commit();
            Intent i = new Intent("com.example.gaurav.login2.Help");
            startActivity(i);
        }

        */
        /**********************************************************/

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if (status != ConnectionResult.SUCCESS) {

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        } else {

            mBtnFind = (Button) findViewById(R.id.btn_show);
            etPlace = (EditText) findViewById(R.id.et_place);

            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            googleMap = fm.getMap();
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.getUiSettings().setRotateGesturesEnabled(true);
            googleMap.getUiSettings().setAllGesturesEnabled(true);

            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);




            mBtnFind.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    String advice = etPlace.getText().toString();

                    if (advice == null || advice.equals("")) {
                        Toast.makeText(getBaseContext(), "No Advice No is entered", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    else
                    {

                        Toast.makeText(getBaseContext(), "tracking", Toast.LENGTH_SHORT).show();

                       String lc=locationDataBaseAdapter.getcount(advice);
                  //     int locationCount = Integer.parseInt(lc);
                        Toast.makeText(getBaseContext(),"Locations detected:"+lc, Toast.LENGTH_SHORT).show();

                      // if (locationCount != 0) {

                        String lat="";
                        String lng ="";
                        String time1 ="";
                        String plat="";
                        String plong="";

                        for (int i = 0; i < Integer.parseInt(lc); i++) {
                       // int i=0;
                           lat = locationDataBaseAdapter.getlatitude(advice,i);
                           lng = locationDataBaseAdapter.getlongitude(advice,i);
                           time1 = locationDataBaseAdapter.gettime(advice,i);

                      //  Toast.makeText(getBaseContext(),lat, Toast.LENGTH_SHORT).show();
                         //  if (lat != "0" && lng != "0") {

                                drawMarker(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)), i + 1, time1);
                                drawCircle(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)));

                            if(i!=0)
                            {

                                LatLng prev = new LatLng(Double.parseDouble(plat),Double.parseDouble(plong));
                                LatLng now = new LatLng(Double.parseDouble(lat),Double.parseDouble(lng));
                                Polyline line = googleMap.addPolyline(new PolylineOptions().add(prev, now)
                                        .width(5).color(Color.BLUE));
                            }
                           // }
                            plat=lat;
                            plong=lng;

                        }
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))));
                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                      // }

                }}

            });

        }
    }
        private void drawCircle(LatLng point){
                    CircleOptions circleOptions = new CircleOptions();
                    circleOptions.center(point);
                    circleOptions.radius(25);
                    circleOptions.strokeColor(Color.BLACK);
                    circleOptions.fillColor(0x30ff0000);
                    circleOptions.strokeWidth(2);
                    googleMap.addCircle(circleOptions);

                }

                private void drawMarker(LatLng point,int sr,String time){
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(point);
                    markerOptions.title(sr + ".Location Coordinates");
                    markerOptions.snippet(Double.toString(point.latitude) + "," + Double.toString(point.longitude) + "\n Time:" + time);
                    googleMap.addMarker(markerOptions);

                }


                @Override
                public boolean onCreateOptionsMenu(Menu menu) {
                    getMenuInflater().inflate(R.menu.main, menu);
                    return true;
                }


                @Override
                public boolean onOptionsItemSelected(MenuItem item) {
                    // TODO Auto-generated method stub
                    int Itemid = item.getItemId();

                    if(Itemid == R.id.help)
                    {
                        Intent i = new Intent(getApplicationContext(),Help.class);
                        startActivity(i);
                    }
                    else if(Itemid == R.id.action_settings)
                    {
                        //Intent p = new Intent(Settings.ACTION_SYNC_SETTINGS);
                        Intent q=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(q);
                    }

                    else
                    {
                        finish();
                        Intent home=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(home);
                    }
                    return super.onOptionsItemSelected(item);
                }

            @Override
            protected void onDestroy() {
            // TODO Auto-generated method stub
            super.onDestroy();
            }

            }