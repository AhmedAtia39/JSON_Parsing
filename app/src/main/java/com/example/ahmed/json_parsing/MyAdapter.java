package com.example.ahmed.json_parsing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AHMED on 18/05/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.holder> {
    ArrayList<ListItem> items = new ArrayList<>();

    public MyAdapter(ArrayList<ListItem> items, View.OnClickListener onClickListener) {
        this.items = items;
    }


    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        holder newView = new holder(view);

        return newView;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.email.setText(items.get(position).getEmail());
        holder.phone.setText(items.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class holder extends RecyclerView.ViewHolder {

        TextView name, email, phone;

        public holder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_name);
            email = itemView.findViewById(R.id.txt_email);
            phone = itemView.findViewById(R.id.txt_phone);
        }
    }
}

