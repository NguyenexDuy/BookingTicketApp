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
import com.example.cnpmnc.model.Ghe;
import com.example.cnpmnc.model.HangKhach;

import java.util.ArrayList;

public class HangKhachChonGheAdapter extends RecyclerView.Adapter<HangKhachChonGheAdapter.HangKhachChonGheViewHolder>{

    private Context context;
    private ArrayList<HangKhach> hangKhaches;
    Ghe ghe;
    private  int number;
    private  int positonOK;

    public HangKhachChonGheAdapter(Context context, ArrayList<HangKhach> hangKhaches) {
        this.context = context;
        this.hangKhaches = hangKhaches;
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
        holder.tv_soGhe.setText(String.valueOf(hangKhach.getSoghe()));
        position= positonOK;
    }

    @Override
    public int getItemCount() {
        return hangKhaches.size();
    }


//    public void setSelectedSeat( long seatNumber) {
////        if (position >= 0 && position < hangKhaches.size()) {
////            HangKhach hangKhach = hangKhaches.get(position);
////            if (hangKhach != null) {
////                hangKhach.setSoGhe(seatNumber);
////                notifyItemChanged(position);
////            }
////        }
//        HangKhach hangKhach=hangKhaches.get(positonOK);
//        if(positonOK>=0&&positonOK<hangKhaches.size())
//        {
//            if(hangKhach!=null)
//            {
//                hangKhach.setSoGhe(seatNumber);
//                notifyDataSetChanged();
//            }
//            positonOK++;
//        }
//        else {
//            Toast.makeText(context, "Het dc chon r thang ngu", Toast.LENGTH_SHORT).show();
//        }
//
//    }
public void setSelectedSeat(long seatNumber) {
    if (positonOK >= 0 && positonOK < hangKhaches.size()) {
        HangKhach hangKhach = hangKhaches.get(positonOK);
        if (hangKhach != null) {
            hangKhach.setSoGhe(seatNumber);
            notifyItemChanged(positonOK);
            positonOK++; // Tăng vị trí đã chọn sau khi gán số ghế
        }
    } else {
        Toast.makeText(context, "Không thể chọn ghế nữa", Toast.LENGTH_SHORT).show();
    }
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
