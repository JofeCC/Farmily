package model;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Listing implements Serializable {


    private String id;
    private String title;
    private String description;
    private String  imagePath;
    private float price;
    private int stock;
    private float rating;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;

    private Address deliveryArea;

    public Listing(){}

    public Address getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(Address deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", rating=" + rating +
                ", deliveryArea=" + (deliveryArea != null ? deliveryArea.getCity() : "null") +
                '}';
    }

    public Listing copy() {
        Listing copy = new Listing();
        copy.title = this.title;
        copy.description = this.description;
        copy.price = this.price;
        copy.imagePath = this.imagePath;
        return copy;
    }

    public static ArrayList<Listing> findAll(View v){
        ArrayList<Listing> rList = new ArrayList<Listing>();


        Snackbar.make(v,
                "showDatabase",
                Snackbar.LENGTH_LONG).show();


        return rList;
    }

}
