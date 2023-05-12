package com.example.mirealtravel;

import android.os.Parcel;
import android.os.Parcelable;

public class Tour implements Parcelable {
    private int id;
    private String name;
    private String description;
    private int starRating;
    private String imageUrl;
    private double price;

    public Tour(int id, String name, String description, int starRating, String imageUrl, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.starRating = starRating;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    protected Tour(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        starRating = in.readInt();
        imageUrl = in.readString();
        price = in.readDouble();
    }

    public static final Creator<Tour> CREATOR = new Creator<Tour>() {
        @Override
        public Tour createFromParcel(Parcel in) {
            return new Tour(in);
        }

        @Override
        public Tour[] newArray(int size) {
            return new Tour[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(starRating);
        dest.writeString(imageUrl);
        dest.writeDouble(price);
    }
}


