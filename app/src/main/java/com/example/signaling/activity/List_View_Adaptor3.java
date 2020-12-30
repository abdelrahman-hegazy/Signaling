package com.example.signaling.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.signaling.R;
import com.example.signaling.helper.cart;
import com.example.signaling.helper.products;

import java.util.ArrayList;


public class List_View_Adaptor3 extends ArrayAdapter<cart> {

    private static final String TAG = "List_View_Adapter3";
    Context mContext;
    int mResource;


    public List_View_Adaptor3(Context context, int resource, ArrayList<cart> Arraycart){
        super(context, resource, Arraycart);
        mContext = context;
        mResource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int id = getItem(position).getId();
        final String userID = getItem(position).getUserID();
        final String shopName = getItem(position).getShopName();
        final String productName = getItem(position).getProductName();
        final String price = getItem(position).getPrice();
        final String offers = getItem(position).getOffers();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);


        TextView tvname = (TextView) convertView.findViewById(R.id.tvnameShop);
        TextView tvproduct = (TextView) convertView.findViewById(R.id.tvnameProduct);
        TextView tvprice = (TextView) convertView.findViewById(R.id.tvprice);
        TextView tvoffers = (TextView) convertView.findViewById(R.id.tvoffers);
        Button delete = convertView.findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveActivity.saveShop(userID,shopName, productName, price, offers);
            }
        });




        tvname.setText(shopName);
        tvproduct.setText(productName);
        tvprice.setText(price);
        tvoffers.setText(offers);





        return convertView;
    }
}
