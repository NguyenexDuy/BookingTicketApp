package com.example.cnpmnc.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.ChiTietFlightActivity;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ChuyenBayDaThichAdapter extends RecyclerView.Adapter<ChuyenBayDaThichAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ChuyenBay> favoriteFlights = new ArrayList<>();
    ArrayList<ChuyenBay> chuyenBaysLiked;

    public ChuyenBayDaThichAdapter(Context context, ArrayList<ChuyenBay> chuyenBaysLiked) {
        this.context = context;
        this.chuyenBaysLiked = chuyenBaysLiked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flightdathich,parent,false);
        ViewHolder chuyenbayYeuThichVH =new ViewHolder(view);
        return chuyenbayYeuThichVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ChuyenBay currentFlight = chuyenBaysLiked.get(position);
        holder.priceflight.setText(String.format("%,d", Math.round(100000)) + " VNĐ");
        holder.nameflight.setText(currentFlight.getDiemDen());
        Glide.with(context).load(currentFlight.getHinhAnh()).into(holder.imageflight);
        holder.imageflight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ChiTietFlightActivity.class);
                intent.putExtra("ThongTinChuyenBay",currentFlight);
                context.startActivity(intent);
            }
        });
        holder.img_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.img_liked.setBackgroundResource(R.drawable.heart_24);
                deleteSachYeuThich(currentFlight ,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return chuyenBaysLiked.size();
    }


    public void deleteSachYeuThich(ChuyenBay chuyenBay,int position)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docref = db.collection("favorites").document(chuyenBay.getIdChuyenBay());
        docref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                chuyenBaysLiked.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Đã xóa khỏi danh sách !!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Xóa không thành công !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameflight, priceflight;
        ImageView imageflight,img_liked;
        public ViewHolder(View itemView) {
            super(itemView);

            nameflight = itemView.findViewById(R.id.tv_nameflight);
            priceflight = itemView.findViewById(R.id.tv_priceflight);
            imageflight = itemView.findViewById(R.id.img_rcvflight);
            img_liked = itemView.findViewById(R.id.img_liked);
        }
    }
}