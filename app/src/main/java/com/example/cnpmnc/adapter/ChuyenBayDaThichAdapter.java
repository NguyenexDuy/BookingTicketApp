package com.example.cnpmnc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.ChiTietFlightActivity;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;

import java.util.ArrayList;

public class ChuyenBayDaThichAdapter extends RecyclerView.Adapter<ChuyenBayDaThichAdapter.ViewHolder>{

    private ArrayList<ChuyenBay> flightlist;
    private Context context;
    private Firebase firebase;
    private ArrayList<ChuyenBay> favoriteFlights = new ArrayList<>();

    public ChuyenBayDaThichAdapter(ArrayList<ChuyenBay> flightlist, Context context) {
        this.flightlist = flightlist;
        this.context = context;
        if (favoriteFlights == null) {
            favoriteFlights = new ArrayList<>();
        }
    }

    public ChuyenBayDaThichAdapter(Context context, ArrayList<ChuyenBay> flightlist, Firebase firebase) {
        this.flightlist = flightlist;
        this.context = context;
        this.firebase = firebase;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flightdathich, parent, false);
        return new ChuyenBayDaThichAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuyenBay currentFlight = flightlist.get(position);
        holder.priceflight.setText(String.format("%,d", Math.round(100000)) + " VNĐ");
        holder.nameflight.setText(currentFlight.getDiemDen());
        Glide.with(context).load(currentFlight.getHinhAnh()).into(holder.imageflight);

        holder.imageflight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Chuyển qua xem chi tiết", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, ChiTietFlightActivity.class);
                intent.putExtra("ThongTinChuyenBay",currentFlight);
                context.startActivity(intent);
            }
        });
        holder.tvXoa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(currentFlight);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return flightlist.size();
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(ChuyenBay chuyenBay);
    }

    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameflight, priceflight,tvXoa;
        ImageView imageflight;
        public ViewHolder(View itemView) {
            super(itemView);

            nameflight = itemView.findViewById(R.id.tv_nameflight);
            priceflight = itemView.findViewById(R.id.tv_priceflight);
            imageflight = itemView.findViewById(R.id.img_rcvflight);
            tvXoa = itemView.findViewById(R.id.tvXoaItemDathich);

            tvXoa.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(flightlist.get(position));
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}
