package com.example.cnpmnc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.HangKhach;

import java.util.ArrayList;

public class HangKhachChonGheAdapter extends RecyclerView.Adapter<HangKhachChonGheAdapter.HangKhachChonGheViewHolder>{

    private Context context;
    private ArrayList<HangKhach> hangKhaches;
    private  int number;

    public HangKhachChonGheAdapter(Context context, ArrayList<HangKhach> hangKhaches, int number) {
        this.context = context;
        this.hangKhaches = hangKhaches;
        this.number = number;
    }

    @NonNull
    @Override
    public HangKhachChonGheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hangkhachchonghe, parent, false);
        return new HangKhachChonGheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HangKhachChonGheViewHolder holder, int position) {
       HangKhach hangKhach=hangKhaches.get(position);
       holder.tv_tenHangKhachChonChoNgoi.setText(hangKhach.getHoTen());
       holder.tv_loaiHangKhachChonChoNgoi.setText(hangKhach.getType());
    }

    @Override
    public int getItemCount() {
        return number;
    }

    public static class HangKhachChonGheViewHolder extends RecyclerView.ViewHolder{
        TextView tv_loaiHangKhachChonChoNgoi,tv_tenHangKhachChonChoNgoi,tv_soGhe;
        public HangKhachChonGheViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_loaiHangKhachChonChoNgoi=itemView.findViewById(R.id.tv_loaiHangKhachChonChoNgoi);
            tv_tenHangKhachChonChoNgoi=itemView.findViewById(R.id.tv_tenHangKhachChonChoNgoi);
            tv_soGhe=itemView.findViewById(R.id.tv_soGhe);
        }
    }
}
