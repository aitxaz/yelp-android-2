
package com.example.androidtest.web_responses.search_resturant;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;
import java.util.List;

public class Business implements Parcelable {

    private String id;
    private String name;
    private String image_url;
    private Boolean is_closed;
    private String url;
    private int review_count;
    private List<Category> categories;
    private double rating;
    private Coordinates coordinates;
    private List<String> transactions = null;
    private String price;
    private Location location;
    private String phone;
    private String display_phone;
    private double distance;



    protected Business(Parcel in) {
        id = in.readString();
        name = in.readString();
        image_url = in.readString();
        url = in.readString();
        review_count = in.readInt();
        transactions = in.createStringArrayList();
        price = in.readString();
        phone = in.readString();
        display_phone = in.readString();
    }

    public static final Creator<Business> CREATOR = new Creator<Business>() {
        @Override
        public Business createFromParcel(Parcel in) {
            return new Business(in);
        }

        @Override
        public Business[] newArray(int size) {
            return new Business[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
    }

    public Boolean getIsClosed() {
        return is_closed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.is_closed = isClosed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReviewCount() {
        return review_count;
    }

    public void setReviewCount(int reviewCount) {
        this.review_count = reviewCount;
    }

    public List<Category> getCategories() {

        if (categories == null) {
            return new ArrayList<>();
        } else {
            return categories;

        }
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplayPhone() {
        return display_phone;
    }

    public void setDisplayPhone(String displayPhone) {
        this.display_phone = displayPhone;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(image_url);
        dest.writeString(url);
        dest.writeInt(review_count);
        dest.writeStringList(transactions);
        dest.writeString(price);
        dest.writeString(phone);
        dest.writeString(display_phone);
    }

    @Override
    public String toString() {
        return "Business{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", is_closed=" + is_closed +
                ", url='" + url + '\'' +
                ", review_count=" + review_count +
                ", categories=" + categories +
                ", rating=" + rating +
                ", coordinates=" + coordinates +
                ", transactions=" + transactions +
                ", price='" + price + '\'' +
                ", location=" + location +
                ", phone='" + phone + '\'' +
                ", display_phone='" + display_phone + '\'' +
                ", distance=" + distance +
                '}';
    }
}
