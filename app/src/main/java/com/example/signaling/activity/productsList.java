package com.example.signaling.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.*;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.example.signaling.app.AppConfig;
import com.example.signaling.app.AppController;
import com.example.signaling.helper.products;
import com.example.signaling.R;

public class productsList extends AppCompatActivity {

    ListView List;
    ArrayList<products> ArrayProducts = new ArrayList<products>();
    ArrayList<String> productNames = new ArrayList<String >();
    String tag_string_req = "Products req";
    private static final String TAG = productsList.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        List = (ListView) findViewById(R.id.productsList);

        products();

    }

    public void products(){

        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.URL_PRODUCT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "products Response: " + response.toString());

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        ArrayProducts.add(new products(jObj.getInt("id"), jObj.getString("name"),
                                jObj.getString("description"), jObj.getString("image")));

                        productNames.add(jObj.getString("name"));
                    }
                    List.setAdapter(new List_View_Adaptor(productsList.this, R.layout.listview_item, ArrayProducts));

                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getApplicationContext(), shopsList.class);
                            startActivity(i);

                        }
                    });




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

}
