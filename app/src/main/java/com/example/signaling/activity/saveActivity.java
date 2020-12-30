package com.example.signaling.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.signaling.R;
import com.example.signaling.app.AppConfig;
import com.example.signaling.app.AppController;
import com.example.signaling.helper.SaveHandler;
import com.example.signaling.helper.link;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class saveActivity extends AppCompatActivity {

    private static String tag_string_req;
    private static final String TAG = saveActivity.class.getSimpleName();
    private static SaveHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);


    }


    public static void saveShop(final String userID, final String shopName, final String productName, final String price, final String offers){

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_SAVE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "save Response: " + response.toString());

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Log.d(TAG, " 88888888888888888888888888888888" + userID);
                    db.addShop(userID,shopName,productName,price,offers);


                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("userID", userID);
                params.put("shopName", shopName);
                params.put("productName", productName);
                params.put("price", price);
                params.put("offers", offers);

                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
