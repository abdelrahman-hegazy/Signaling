package com.example.signaling.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.example.signaling.R;
import com.example.signaling.helper.products;


public class List_View_Adaptor extends ArrayAdapter<products> {

    private static final String TAG = "List_View_Adapter";
    Context mContext;
    int mResource;
    static int idp;


    public List_View_Adaptor(Context context, int resource, ArrayList<products> products){
        super(context, resource, products);
        mContext = context;
        mResource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int id = getItem(position).id;
        idp = id;
        String image = getItem(position).image;
        String name = getItem(position).name;
        String description = getItem(position).description;

        products products = new products(id, name, description, image);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);


        TextView tvname = (TextView) convertView.findViewById(R.id.tvname);
        TextView tvdescription = (TextView) convertView.findViewById(R.id.tvdescription);
        ImageView tvimage = (ImageView) convertView.findViewById(R.id.imageView);

        Glide.with(mContext).load(image).diskCacheStrategy(DiskCacheStrategy.NONE).into(tvimage);


        tvname.setText(name);
        tvdescription.setText(description);



        return convertView;
    }


}
