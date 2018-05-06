package com.jacob.orderoid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jacob.orderoid.Data.FoodItem;
import com.jacob.orderoid.Interface.ItemClickListener;
import com.jacob.orderoid.ViewHolder.FoodItemViewHolder;
import com.squareup.picasso.Picasso;

public class FoodActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase database;
    DatabaseReference foodItems;
    String foodType="";
    String categoryId="";

    FirebaseRecyclerAdapter<FoodItem,FoodItemViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        categoryId = getIntent().getStringExtra("categoryId");



        database = FirebaseDatabase.getInstance();
        foodItems = database.getReference("Foods");
        mRecyclerView = (RecyclerView)findViewById(R.id.recycle_food);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Load food menu


        if (!categoryId.isEmpty() && categoryId != null){

            loadFoodList(categoryId);
        }

    }

    private void loadFoodList(String categoryID) {
        adapter = new FirebaseRecyclerAdapter<FoodItem, FoodItemViewHolder>(FoodItem.class,
                R.layout.food_item,
                FoodItemViewHolder.class,
                foodItems.orderByChild("CategoryId").equalTo(categoryID))
                //above means select * from ... where CategoryId = categoryid
        {
            @Override
            protected void populateViewHolder(FoodItemViewHolder viewHolder, FoodItem model, int position) {
                viewHolder.foodName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(viewHolder.foodImage);
                final FoodItem local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodActivity.this, "" + local.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        mRecyclerView.setAdapter(adapter);
    }
}
