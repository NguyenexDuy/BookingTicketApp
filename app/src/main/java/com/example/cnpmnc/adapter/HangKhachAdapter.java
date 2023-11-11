package com.example.cnpmnc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.NhapThongTinHangKhachNguoiLonActivity;
import com.example.cnpmnc.activity.NhapThongTinHangKhachTreEmActivity;
import com.example.cnpmnc.model.HangKhach;

import java.util.ArrayList;

public class HangKhachAdapter extends  RecyclerView.Adapter<HangKhachAdapter.HangKhachViewHolder> {
    private int number;
    private Context context;
    private ArrayList<HangKhach> mHangKhach;
    private int adapterType;


    public HangKhachAdapter(int number, ArrayList<HangKhach> mHangKhach,Context mcontext,int adapterType) {
        this.number = number;
        this.mHangKhach = mHangKhach;
        this.context=mcontext;
        this.adapterType=adapterType;
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
        holder.tv_tenHangKhach.setText(hangKhach.getHoTen());
        holder.tv_loaiHangKhach.setText(hangKhach.getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               switch (adapterType)
               {
                   case 1:
                       Intent intent=new Intent(context, NhapThongTinHangKhachNguoiLonActivity.class);
                       intent.putExtra("position",position);
                       context.startActivity(intent);
                       break;
                   case 2:
                       Intent intent1=new Intent(context, NhapThongTinHangKhachTreEmActivity.class);
                       intent1.putExtra("position1",position);
                       context.startActivity(intent1);
                       break;
               }
            }
        });

    }

    @Override
    public int getItemCount() {
        return number;
    }

    public class HangKhachViewHolder extends RecyclerView.ViewHolder{

        TextView tv_tenHangKhach,tv_hangKhach,tv_loaiHangKhach,tv_dienThongTin;
        public HangKhachViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenHangKhach =itemView.findViewById(R.id.tv_tenHangKhach);
            tv_hangKhach=itemView.findViewById(R.id.tv_hangKhach);
            tv_loaiHangKhach=itemView.findViewById(R.id.tv_loaiHangKhach);
            tv_dienThongTin=itemView.findViewById(R.id.tv_dienThongTin);
        }
    }
}
