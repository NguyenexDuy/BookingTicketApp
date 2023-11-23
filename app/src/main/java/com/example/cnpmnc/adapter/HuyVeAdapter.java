package com.example.cnpmnc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.VeMayBay;

import java.util.ArrayList;

public class HuyVeAdapter extends RecyclerView.Adapter<HuyVeAdapter.HuyVeVH>{
    private Context context;
    private ArrayList<VeMayBay> huyVe;

    public HuyVeAdapter(Context context, ArrayList<VeMayBay> huyVe) {
        this.context = context;
        this.huyVe = huyVe;
    }

    @NonNull
    @Override
    public HuyVeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehuy,parent,false);
        return new HuyVeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HuyVeVH holder, int position) {
        VeMayBay vehuy=huyVe.get(position);
        holder.tv_idVeHuy.setText(vehuy.getIdVe());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Ngu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return huyVe.size();
    }

    public class HuyVeVH extends RecyclerView.ViewHolder{
        TextView tv_idVeHuy;
        public HuyVeVH(@NonNull View itemView) {
            super(itemView);
            tv_idVeHuy=itemView.findViewById(R.id.tv_idVeHuy);
        }
    }
}
