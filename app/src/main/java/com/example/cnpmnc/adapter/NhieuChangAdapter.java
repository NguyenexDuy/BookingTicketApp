package com.example.cnpmnc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.ChuyenBay;

import java.util.ArrayList;

public class NhieuChangAdapter extends RecyclerView.Adapter<NhieuChangAdapter.NhieuChangViewHolder>{

    ArrayList<ChuyenBay> chuyenBays;
    Context context;

    public NhieuChangAdapter(ArrayList<ChuyenBay> chuyenBays, Context context) {
        this.chuyenBays = chuyenBays;
        this.context = context;
    }


    @NonNull
    @Override
    public NhieuChangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhieuchang,parent,false);
        return new NhieuChangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhieuChangViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return chuyenBays.size();
    }
    public void addItem(ChuyenBay newItem) {
        chuyenBays.add(newItem);
    }

    public class NhieuChangViewHolder extends RecyclerView.ViewHolder{

       TextView tv_idsanbaydiemdiNhieuChang,tv_idsanbaydiemdenNhieuChang,tv_CalendarNgayDiNhieuChang;
       public NhieuChangViewHolder(@NonNull View itemView) {
           super(itemView);
           tv_idsanbaydiemdiNhieuChang=itemView.findViewById(R.id.tv_idsanbaydiemdiNhieuChang);
           tv_idsanbaydiemdenNhieuChang=itemView.findViewById(R.id.tv_idsanbaydiemdenNhieuChang);
           tv_CalendarNgayDiNhieuChang=itemView.findViewById(R.id.tv_CalendarNgayDiNhieuChang);
       }
   }
}
