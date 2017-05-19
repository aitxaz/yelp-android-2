package com.example.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.androidtest.items.Globals;
import com.example.androidtest.web_responses.search_resturant.Business;
import com.example.androidtest.web_responses.search_resturant.Category;

import java.util.List;

public class ResturantDetails extends AppCompatActivity {
    ImageView restImage;
    TextView resturantName, phoneNumber, restRating, restReviews;
    RecyclerView recycler_view;
    Adapter mAdapter;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_details);
        restImage = (ImageView) findViewById(R.id.restImage);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        resturantName = (TextView) findViewById(R.id.resturantName);
        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        restRating = (TextView) findViewById(R.id.restRating);
        restReviews = (TextView) findViewById(R.id.restReviews);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setEnabled(false);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Business business = bundle.getParcelable(Globals.RESTURANT);
        List<Category> categories = bundle.getParcelableArrayList(Globals.CATEGORIES);
        if (business != null) {
            Glide.with(this).load(business.getImageUrl())
                    .thumbnail(0.5f)
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(restImage);
            resturantName.setText(business.getName());
            phoneNumber.setText("Phone# " + business.getDisplayPhone());
//            restRating.setText("Rating: " + bundle.getString(Globals.RATING));
            ratingBar.setRating(Float.parseFloat(bundle.getString(Globals.RATING)));

            restReviews.setText("" + business.getReviewCount() + " reviews");
            recycler_view.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recycler_view.setLayoutManager(mLayoutManager);
            mAdapter = new Adapter(categories, this);
            recycler_view.setAdapter(mAdapter);

        }
    }
}
