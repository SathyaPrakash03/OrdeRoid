package com.jacob.orderoid.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jacob.orderoid.Interface.ItemClickListener;
import com.jacob.orderoid.R;


public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView menuName;
    public ImageView menuImage;

    private ItemClickListener mItemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);

        menuName = (TextView)itemView.findViewById(R.id.menuName);
        menuImage = (ImageView) itemView.findViewById(R.id.menuImage);

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