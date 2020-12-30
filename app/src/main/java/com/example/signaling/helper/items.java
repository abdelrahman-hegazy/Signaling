package com.example.signaling.helper;

public class items implements Comparable<items> {

    String name;
    String price;
    String offers;
    String distance;



    public items(String name, String price, String offers, String distance){
        this.name = name;
        this.price = price;
        this.offers = offers;
        this.distance = distance;

    }
    @Override
    public int compareTo(items other) {
        int p = Integer.parseInt(this.price);
        int p1 = Integer.parseInt(other.price);
        return p-p1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
