package com.example.signaling.activity;


import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.signaling.R;
import com.example.signaling.helper.items;
import com.example.signaling.helper.link;
import com.example.signaling.helper.products;
import com.example.signaling.helper.shops;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class List_View_Adaptor2 extends ArrayAdapter<link> {

    private static final String TAG = "List_View_Adapter";

    Context mContext;
    int mResource;
    ArrayList<shops> shops;
    ArrayList<link> link;
    ArrayList<products> products;
    Task<Location> task;
    static ArrayList<items> items = new ArrayList<>();





    public List_View_Adaptor2(Context context, int resource, ArrayList<link> link, ArrayList<shops> shops, ArrayList<products> products, Task<Location> task) {
        super(context, resource, link);
        mContext = context;
        mResource = resource;
        this.link = link;
        this.shops = shops;
        this.task = task;
        this.products = products;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        shops shopsA = shops.get(position);
        final products productsA = products.get(position);
        final String userID = LoginActivity.userID;


        Log.d(TAG, " 33333333333333333333333333333333333333333" + shops.size());
        Log.d(TAG, " 55555555555555555555555555555555555555555" + link.size());

        int id = getItem(position).id;
        final String name = shopsA.name;
        final String price = getItem(position).price;
        final String offers = getItem(position).offers;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.tvname);
        TextView tvprice = (TextView) convertView.findViewById(R.id.tvprice);
        TextView tvoffers = (TextView) convertView.findViewById(R.id.tvoffers);
        TextView tvdistance = (TextView) convertView.findViewById(R.id.tvdistance);
        Button save = convertView.findViewById(R.id.save);


        Location shop = new Location("shop");
        shop.setLatitude(shopsA.latitude);
        shop.setLongitude(shopsA.longitude);

        Location user = new Location("User") ;
        //user.setLatitude(task.getResult().getLatitude());
        //user.setLongitude(task.getResult().getLongitude());

        //int d = (int) (user.distanceTo(shop)/1000) ;
        String s = Integer.toString(5);

        items.add(new items(name, price, offers, s));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, " 9999999999999999999999999999999999999999" + userID);
                saveActivity.saveShop(userID, name, productsA.name, price, offers);
            }
        });


        tvname.setText(name);
        tvprice.setText("Price: " + price);
        if(offers.isEmpty()){
            tvoffers.setText("No Special offers");
        }else{
            tvoffers.setText(offers);
        }
        tvdistance.setText("Distance: "+ s);


        return convertView;
    }


}
