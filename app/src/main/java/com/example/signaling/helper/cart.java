package com.example.signaling.helper;

public class cart {

    int id;
    String userID;
    String shopName;
    String productName;
    String price;
    String offers;

    public cart(int id, String userID, String shopName, String productName, String price, String offers) {
        this.id = id;
        this.userID = userID;
        this.shopName = shopName;
        this.productName = productName;
        this.price = price;
        this.offers = offers;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }
}
