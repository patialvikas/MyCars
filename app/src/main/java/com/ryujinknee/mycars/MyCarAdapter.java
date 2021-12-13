package com.ryujinknee.mycars;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyCarAdapter extends RecyclerView.Adapter<MyCarAdapter.ViewHolder>{
    //private final List<CarsModel> listdata;
    private List<Car> listdata;
    private HomeActivity homeActivity;
    // RecyclerView recyclerView;
    public MyCarAdapter(HomeActivity homeActivity, List<Car> listdata) {
        this.listdata = listdata;
        this.homeActivity=homeActivity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.car_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car car = listdata.get(position);
       // final List<Car> myListData = listdata.get(position).getCars();
        holder.name.setText(car.getName());
        holder.model.setText(car.getModel());
        holder.color.setText(car.getColors());
        /*holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView model;
        public TextView color;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.carname);
            this.model = (TextView) itemView.findViewById(R.id.carmodel);
            this.color = (TextView) itemView.findViewById(R.id.colorcar);
        }
    }
}