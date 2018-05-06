package com.jacob.orderoid.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jacob.orderoid.Interface.ItemClickListener;
import com.jacob.orderoid.R;


public class FoodItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView foodName;
    public ImageView foodImage;

    private ItemClickListener mItemClickListener;

    public FoodItemViewHolder(View itemView) {
        super(itemView);

        foodName = (TextView)itemView.findViewById(R.id.foodName);
        foodImage = (ImageView) itemView.findViewById(R.id.foodImage);

        itemView.setOnClickListener(this);

    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
