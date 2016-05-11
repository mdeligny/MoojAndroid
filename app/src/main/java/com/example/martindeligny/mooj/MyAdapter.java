package com.example.martindeligny.mooj;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final List<Deal> deals;


    public MyAdapter(ArrayList<Deal> deals) {
        //Log.i("first deal",deals.get(0).getName());
        this.deals = deals;
    }


    @Override
    public int getItemCount() {
        return deals.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Deal deal = deals.get(position);
        holder.display(deal);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private final TextView timestamp;
        private final ImageView image;

        private Deal currentDeal;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));
            timestamp = ((TextView) itemView.findViewById(R.id.timestamp));
            image = ((ImageView) itemView.findViewById(R.id.imageView));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentDeal.name)
                            .show();
                }
            });
        }

        public void display(Deal deal) {
            currentDeal = deal;
            name.setText(deal.getName());
            description.setText(deal.getDescription());
            timestamp.setText(deal.getDate().toString());
        }
    }

}