package com.example.signaling.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.signaling.R;
import com.example.signaling.app.AppConfig;
import com.example.signaling.app.AppController;
import com.example.signaling.helper.cart;
import com.example.signaling.helper.products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class cartActivity extends AppCompatActivity {
    ListView List;
    String tag_string_req = "Products req";
    private static final String TAG = productsList.class.getSimpleName();
    static ArrayList<cart> Arraycart = new ArrayList<cart>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        List = (ListView) findViewById(R.id.saveList);

        cart();

    }

    public void cart(){

        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.URL_CART, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "products Response: " + response.toString());

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        Arraycart.add(new cart(jObj.getInt("id"), jObj.getString("userID"), jObj.getString("shopName"),
                                jObj.getString("productName"), jObj.getString("price"), jObj.getString("offers")));
                    }
                    List.setAdapter(new List_View_Adaptor3(cartActivity.this, R.layout.listview_item3, Arraycart));

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