package com.example.cnpmnc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.ChiTietVeMayBayActivity;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;
import com.example.cnpmnc.model.VeMayBay;

import java.util.ArrayList;

public class NotifiCationAdapter extends RecyclerView.Adapter<NotifiCationAdapter.NofificationVH>{
    private Context context;
    private ArrayList<VeMayBay> notificaiton;
    Firebase firebase;

    public NotifiCationAdapter(Context context, ArrayList<VeMayBay> notificaiton, Firebase firebase) {
        this.context = context;
        this.notificaiton = notificaiton;
        this.firebase=firebase;
    }

    @NonNull
    @Override
    public NofificationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
        return new NofificationVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NofificationVH holder, int position) {
        VeMayBay notifi=notificaiton.get(position);
        holder.tv_giaveNoti.setText(notifi.getGiaVe());
//        holder.tv_usernameNoti.setText(notifi.getIdHangKhach());
        String idHangKhach=notifi.getIdHangKhach();
        firebase.getTenHangKhachById(idHangKhach, new Firebase.getTenHangKhachByIdCallback() {
            @Override
            public void onCallBack(String tenHangKhach) {
                holder.tv_usernameNoti.setText(tenHangKhach);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ChiTietVeMayBayActivity.class);
                intent.putExtra("VeMayBay",notifi);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificaiton.size();
    }

    public class NofificationVH extends RecyclerView.ViewHolder{
        TextView tv_usernameNoti,tv_giaveNoti;
        public NofificationVH(@NonNull View itemView) {
            super(itemView);
            tv_usernameNoti=itemView.findViewById(R.id.tv_usernameNoti);
            tv_giaveNoti=itemView.findViewById(R.id.tv_giaveNoti);
        }
    }
}
