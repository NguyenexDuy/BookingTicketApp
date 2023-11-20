package com.example.cnpmnc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.Ghe;

import java.util.ArrayList;

public class GheAdapter extends RecyclerView.Adapter<GheAdapter.GheViewHolder>{
    private Context context;
    private ArrayList<Ghe> ghes;

    public GheAdapter(Context context, ArrayList<Ghe> ghes) {
        this.context = context;
        this.ghes = ghes;
    }

    @NonNull
    @Override
    public GheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chonchongoi, parent, false);
        return new GheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GheViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return ghes.size();
    }

    public static class GheViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;

        public GheViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox);
        }
    }
}
