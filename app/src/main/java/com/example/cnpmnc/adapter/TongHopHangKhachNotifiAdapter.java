package com.example.cnpmnc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cnpmnc.R;
import com.example.cnpmnc.model.HangKhach;
import java.util.ArrayList;
import java.util.Map;

public class TongHopHangKhachNotifiAdapter extends RecyclerView.Adapter<TongHopHangKhachNotifiAdapter.TongHopHangKhachNififiViewHolder>{

    private Context context;
    private ArrayList<Map<String, Object>> dataList;

    public TongHopHangKhachNotifiAdapter(Context context, ArrayList<Map<String, Object>> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public TongHopHangKhachNififiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hangkhachnotificaiton, parent, false);
        return new TongHopHangKhachNififiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TongHopHangKhachNififiViewHolder holder, int position) {
        Map<String, Object> item = dataList.get(position);
        if (item != null) {
            String name = (String) item.get("name");
            int soGhe = ((Long) item.get("soGhe")).intValue();
            String type = (String) item.get("type");

            // Hiển thị dữ liệu trong ViewHolder
            holder.tv_tenHK.setText(name);
            holder.tv_soG.setText(String.valueOf(soGhe));
            holder.tv_loaiHK.setText(type);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class TongHopHangKhachNififiViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tenHK, tv_soG, tv_loaiHK;

        public TongHopHangKhachNififiViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenHK = itemView.findViewById(R.id.tv_tenHK);
            tv_soG = itemView.findViewById(R.id.tv_soG);
            tv_loaiHK = itemView.findViewById(R.id.tv_loaiHK);
        }
    }
}