package com.example.signaling.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.signaling.R;
import com.example.signaling.helper.link;
import com.example.signaling.helper.products;
import com.example.signaling.helper.shops;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class List_View_Adaptor2 extends ArrayAdapter<link> {

    private static final String TAG = "List_View_Adapter";

    Context mContext;
    int mResource;
    ArrayList<shops> shops;
    ArrayList<link> link;
    Task<Location> task;




    public List_View_Adaptor2(Context context, int resource, ArrayList<link> link, ArrayList<shops> shops, Task<Location> task) {
        super(context, resource, link);
        mContext = context;
        mResource = resource;
        this.link = link;
        this.shops = shops;
        this.task = task;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        shops shopsA = shops.get(position);
        Log.d(TAG, " 33333333333333333333333333333333333333333" + shops.size());
        Log.d(TAG, " 55555555555555555555555555555555555555555" + link.size());

        int id = getItem(position).id;
        String name = shopsA.name;
        String price = getItem(position).price;
        String offers = getItem(position).offers;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.tvname);
        TextView tvprice = (TextView) convertView.findViewById(R.id.tvprice);
        TextView tvoffers = (TextView) convertView.findViewById(R.id.tvoffers);
        TextView tvdistance = (TextView) convertView.findViewById(R.id.tvdistance);


        Location shop = new Location("shop");
        shop.setLatitude(shopsA.latitude);
        shop.setLongitude(shopsA.longitude);

        Location user = new Location("User") ;
        //user.setLatitude(task.getResult().getLatitude());
        //user.setLongitude(task.getResult().getLongitude());

        //int d = (int) (user.distanceTo(shop)/1000) ;
        //String s = Integer.toString(d);


        tvname.setText(name);
        tvprice.setText(price);
        tvoffers.setText(offers);
        //tvdistance.setText(s);


        return convertView;
    }


}
