package com.example.signaling.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.signaling.R;
import com.example.signaling.app.AppConfig;
import com.example.signaling.app.AppController;
import com.example.signaling.helper.link;
import com.example.signaling.helper.products;
import com.example.signaling.helper.shops;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class shopsList extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    ListView List;
    ArrayList<link> ArrayLink = new ArrayList<link>();
    ArrayList<shops> ArrayShops = new ArrayList<shops>();
    String tag_string_req = "Products req";
    private static final String TAG = productsList.class.getSimpleName();
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        List = (ListView) findViewById(R.id.shopsList);


        link(3);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



    }

    public void link(final int id){

        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.URL_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "link Response: " + response.toString());

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        if(jObj.getInt("product_id") == id) {

                            ArrayLink.add(new link(jObj.getInt("id"), jObj.getInt("product_id"), jObj.getInt("shop_id")
                                    , jObj.getString("price"), jObj.getString("offers")));

                            shops(jObj.getInt("shop_id"));
                        }
                    }


                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void shops(final int id){

        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.URL_SHOP, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "shops Response: " + response.toString());

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        if(jObj.getInt("id") == id) {

                            ArrayShops.add(new shops(jObj.getInt("id"), jObj.getString("name"), Double.parseDouble(jObj.getString("longitude"))
                                    , Double.parseDouble(jObj.getString("latitude"))));
                        }

                    }
                    if(ArrayLink.size() == ArrayShops.size()){

                        List.setAdapter(new List_View_Adaptor2(shopsList.this, R.layout.listview_item2, ArrayLink, ArrayShops, fetchLocation()));
                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private Task<Location> fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        return task;
    }

}
