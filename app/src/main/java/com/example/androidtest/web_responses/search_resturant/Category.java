
package com.example.androidtest.web_responses.search_resturant;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Category implements Parcelable {


    private String alias;

    private String title;

    protected Category(Parcel in) {
        alias = in.readString();
        title = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(alias);
        dest.writeString(title);
    }
}
