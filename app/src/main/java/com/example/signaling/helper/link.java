package com.example.signaling.helper;

public class link {

    public int id;
    public int product_id;
    public int shop_id;
    public String price;
    public String offers;


    public link(int id, int product_id, int shop_id, String price, String offers) {
        this.id = id;
        this.product_id = product_id;
        this.shop_id = shop_id;
        this.price = price;
        this.offers = offers;

    }

    public int getIdProduct() {
        return product_id;
    }

    public int getIdShop() {
        return shop_id;
    }

    public String getPrice() {
        return price;
    }

    public String getOffers() {
        return offers;
    }
}
