package com.example.cnpmnc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.HangKhach;

import java.util.ArrayList;

public class HangKhachAdapter extends  RecyclerView.Adapter<HangKhachAdapter.HangKhachViewHolder> {
    private int number;
    private ArrayList<HangKhach> mHangKhach;


    public HangKhachAdapter(int number, ArrayList<HangKhach> mHangKhach) {
        this.number = number;
        this.mHangKhach = mHangKhach;
    }



    @NonNull
    @Override
    public HangKhachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khachhang,parent,false);
        return new HangKhachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HangKhachViewHolder holder, int position) {
        HangKhach hangKhach=mHangKhach.get(position);
        holder.textView1.setText(hangKhach.getHoTen());
    }

    @Override
    public int getItemCount() {
        return number;
    }

    public class HangKhachViewHolder extends RecyclerView.ViewHolder{

        TextView textView1,tv_hangKhach,tv_loaiHangKhach,tv_dienThongTin,textview199;
        ImageView iconabc;
        public HangKhachViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.textview199);
            tv_hangKhach=itemView.findViewById(R.id.tv_hangKhach);
            tv_loaiHangKhach=itemView.findViewById(R.id.tv_loaiHangKhach);
            tv_dienThongTin=itemView.findViewById(R.id.tv_dienThongTin);
        }
    }
}
