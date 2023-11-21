package com.example.cnpmnc.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.ThongTinKhachhangActivity;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;

import java.util.ArrayList;

public class ChuyenBayLoaiThuongGiaAdapter extends RecyclerView.Adapter<ChuyenBayLoaiThuongGiaAdapter.ChuyenBayVH> {
    private Context mcontext;
    private ArrayList<ChuyenBay> chuyenVeTests;
    DiaDiem diaDiem;

    public ChuyenBayLoaiThuongGiaAdapter(ArrayList<ChuyenBay> chuyenVeTests, Context mcontext) {
        this.chuyenVeTests = chuyenVeTests;
        this.mcontext=mcontext;

    }

    @NonNull
    @Override
    public ChuyenBayLoaiThuongGiaAdapter.ChuyenBayVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongtinchuyenbay, parent, false);
        return new ChuyenBayLoaiThuongGiaAdapter.ChuyenBayVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuyenBayLoaiThuongGiaAdapter.ChuyenBayVH holder, int position) {
        ChuyenBay chuyenVeTest = chuyenVeTests.get(position);


        holder.tvMaChuyenBay.setText(chuyenVeTest.getIdChuyenBay());
        holder.tvNgayBay.setText(chuyenVeTest.getNgayDi());
        holder.tvNgayDen.setText(chuyenVeTest.getNgayVe());
        holder.tvGioBay.setText(chuyenVeTest.getGioBatDau());
        holder.tvNoiBay.setText(chuyenVeTest.getDiemDi());
        holder.tvNoiDen.setText(chuyenVeTest.getDiemDen());
        holder.tv_SoLuongVe.setText(chuyenVeTest.getSoLuongGheVipTrong());
        holder.tvTongTien.setText(chuyenVeTest.getGiaVe());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mcontext, ThongTinKhachhangActivity.class);
                intent.putExtra("ChuyenBayData", chuyenVeTest);
                Bundle bundle = new Bundle();
                bundle.putString("LoaiGhe","ThuongGia");
                intent.putExtras(bundle);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chuyenVeTests.size();
    }

    public static class ChuyenBayVH extends RecyclerView.ViewHolder {
        public TextView tvMaChuyenBay;
        public TextView tvNgayBay;
        public TextView tvNgayDen;
        public TextView tvGioBay;
        public TextView tvGioDen;
        public TextView tvNoiBay;
        public TextView tvNoiDen;
        public TextView tvTongTien,tv_SoLuongVe;

        public ChuyenBayVH(@NonNull View itemView) {
            super(itemView);
            tv_SoLuongVe=itemView.findViewById(R.id.tv_SoLuongVe);
            tvMaChuyenBay = itemView.findViewById(R.id.tvMaChuyenBay);
            tvNgayBay = itemView.findViewById(R.id.tvNgayBay);
            tvNgayDen = itemView.findViewById(R.id.tvNgayDen);
            tvGioBay = itemView.findViewById(R.id.tvGioBay);
            tvGioDen = itemView.findViewById(R.id.tvGioDen);
            tvNoiBay = itemView.findViewById(R.id.tvNoiBay);
            tvNoiDen = itemView.findViewById(R.id.tvNoiDen);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);

        }
    }

}
