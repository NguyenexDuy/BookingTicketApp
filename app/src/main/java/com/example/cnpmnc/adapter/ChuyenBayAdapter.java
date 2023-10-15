package com.example.cnpmnc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.ChuyenVeTest;

import java.util.List;

public class ChuyenBayAdapter extends RecyclerView.Adapter<ChuyenBayAdapter.ChuyenBayVH>{
    private List<ChuyenVeTest> chuyenVeTests;

    public ChuyenBayAdapter(List<ChuyenVeTest> chuyenVeTests) {
        this.chuyenVeTests = chuyenVeTests;
    }

    @NonNull
    @Override
    public ChuyenBayVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongtinchuyenbay, parent, false);
        return new ChuyenBayVH( view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuyenBayVH holder, int position) {
        ChuyenVeTest chuyenVeTest = chuyenVeTests.get(position);
        holder.tvMaChuyenBay.setText(chuyenVeTest.getMaChuyenBay());
        holder.tvNgayBay.setText(chuyenVeTest.getNgayBay());
        holder.tvNgayDen.setText(chuyenVeTest.getNgayDen());
        holder.tvGioBay.setText(chuyenVeTest.getGioBay());
        holder.tvGioDen.setText(chuyenVeTest.getGioDen());
        holder.tvNoiBay.setText(chuyenVeTest.getNoiBay());
        holder.tvNoiDen.setText(chuyenVeTest.getNoiDen());
        holder.tvTongTien.setText(chuyenVeTest.getTongTien());
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
        public TextView tvTongTien;

        public ChuyenBayVH(@NonNull View itemView) {
            super(itemView);
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
