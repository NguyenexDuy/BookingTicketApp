package com.example.cnpmnc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cnpmnc.R;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;

import java.util.ArrayList;

public class RcvCateFlightAdapter extends RecyclerView.Adapter<RcvCateFlightAdapter.ViewHolder>{
    private ArrayList<ChuyenBay> flightlist;
    private Context context;
    private Firebase firebase;
    public RcvCateFlightAdapter(Context context, ArrayList<ChuyenBay> flightlist, Firebase firebase) {
        this.flightlist = flightlist;
        this.context = context;
        this.firebase = firebase;
    }

    @Override
    public RcvCateFlightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcvflight, parent, false);
        return new RcvCateFlightAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RcvCateFlightAdapter.ViewHolder holder, int position) {
        ChuyenBay currentFlight = flightlist.get(position);
        holder.priceflight.setText(String.format("%,d", Math.round(100000)) + " VNĐ");
        firebase.getTenSanBayBySanBayId(currentFlight.getDiemDen(), new Firebase.getTenSanBayBySanBayIdCallback() {
            @Override
            public void onCallback(String tensanbay) {
                holder.nameflight.setText(tensanbay);
            }
        });
        Glide.with(context).load(currentFlight.getHinhAnh()).into(holder.imageflight);
        holder.imageflight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Chuyển qua xem chi tiết", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return flightlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameflight, priceflight;
        ImageView imageflight;
        public ViewHolder(View itemView) {
            super(itemView);
            nameflight = itemView.findViewById(R.id.tv_nameflight);
            priceflight = itemView.findViewById(R.id.tv_priceflight);
            imageflight = itemView.findViewById(R.id.img_rcvflight);
        }
    }
}
