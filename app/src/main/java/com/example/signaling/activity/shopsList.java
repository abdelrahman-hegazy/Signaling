package com.example.signaling.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.signaling.R;
import com.example.signaling.app.AppConfig;
import com.example.signaling.app.AppController;
import com.example.signaling.helper.items;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class shopsList extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    ListView List;
    ArrayList<link> ArrayLink = new ArrayList<link>();
    ArrayList<shops> ArrayShops = new ArrayList<shops>();
    String tag_string_req = "Products req";
    private static final String TAG = productsList.class.getSimpleName();
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<products> ArrayProducts = productsList.ArrayProducts;
    Button srtPrice;
    Button srtDistance;
    Button cart;
    ArrayList<items> items = List_View_Adaptor2.items;
    List_View_Adaptor2 adaptor2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        List = (ListView) findViewById(R.id.shopsList);
        TextView tvnamep = (TextView) findViewById(R.id.tvnameP);
        TextView tvdesciptionp = (TextView) findViewById(R.id.tvdescriptionP);
        srtPrice = findViewById(R.id.sortprice);
        srtDistance = findViewById(R.id.sortdistance);
        cart = findViewById(R.id.cart);



        link(3);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        tvnamep.setText(ArrayProducts.get(2).name);
        tvdesciptionp.setText(ArrayProducts.get(2).description);

        srtPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, " 33333333333333333333333333333333333333333sdsdsd"+ items.size());
                Collections.sort(items);
                adaptor2.notifyDataSetChanged();

            }
        });

        srtDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, " 444444444444444444444444444444444444");
                Collections.sort(items, new Comparator<items>() {
                    @Override
                    public int compare(items o1, items o2) {
                        return o1.getDistance().compareTo(o2.getDistance());
                    }
                });
                adaptor2.notifyDataSetChanged();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCart();
            }
        });

    }
    public void openCart(){
        Intent i = new Intent(shopsList.this, cartActivity.class);
        startActivity(i);
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

                        adaptor2 = new List_View_Adaptor2(shopsList.this, R.layout.listview_item2, ArrayLink, ArrayShops, ArrayProducts, fetchLocation());
                        List.setAdapter(adaptor2);
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
