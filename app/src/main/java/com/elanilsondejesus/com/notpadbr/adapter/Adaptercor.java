package com.elanilsondejesus.com.notpadbr.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;

import java.util.List;

public class Adaptercor extends RecyclerView.Adapter<Adaptercor.MyViewHolder> {
    List<Integer> cores;


    public Adaptercor(List<Integer> cores) {
        this.cores = cores;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View lista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cores, parent, false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         Integer i = cores.get(position);
        holder.view.setBackgroundColor(i.intValue());



    }

    @Override
    public int getItemCount() {
        return cores.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.viewCor);




        }






    }


}
