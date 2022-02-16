package com.MyMADAPP.numad22sp_lakshmiposni;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {
    //public ImageView itemIcon;
    public TextView itemName;
    public TextView itemUrl;
    public CheckBox checkBox;

    public Button visit;


    public RviewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
        //itemIcon = itemView.findViewById(R.id.item_icon);
        itemName = itemView.findViewById(R.id.item_name);
        itemUrl = itemView.findViewById(R.id.item_url);
        checkBox = itemView.findViewById(R.id.checkbox);

        visit = itemView.findViewById(R.id.visitBtn);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(position);
                    }
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onCheckBoxClick(position);
                    }
                }
            }
        });
    }

}



















