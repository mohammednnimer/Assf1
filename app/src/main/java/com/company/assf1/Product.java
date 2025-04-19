package com.company.assf1;


public class Product {


    private String id;
    private String name;
    private double price;
    private int quantity;
    private String imageUrl;
    private boolean isAvailable;
    private boolean isForRent;



    boolean ispay=false;





    public Product() {}

    public Product(String id, String name, double price, int quantity,
                   String imageUrl, boolean isAvailable, boolean isForRent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
        this.isForRent = isForRent;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isForRent() {
        return isForRent;
    }

    public void setForRent(boolean forRent) {
        isForRent = forRent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
