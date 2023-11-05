package com.example.cnpmnc.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.ChiTietFlightActivity;
import com.example.cnpmnc.activity.DanhSachChuyenBayDaThich;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RcvCateFlightAdapter extends RecyclerView.Adapter<RcvCateFlightAdapter.ViewHolder>{



    private ArrayList<ChuyenBay> flightlist;
    private Context context;
    private Firebase firebase;
    private ArrayList<ChuyenBay> favoriteFlights = new ArrayList<>();
    private RecyclerView recyclerViewDanhSachYeuThich;
    private RcvCateFlightAdapter danhSachYeuThichAdapter;

    public RcvCateFlightAdapter(ArrayList<ChuyenBay> flightlist, Context context) {
        this.flightlist = flightlist;
        this.context = context;
        if (favoriteFlights == null) {
            favoriteFlights = new ArrayList<>();
        }
    }

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
        CheckBox tymBtn = holder.itemView.findViewById(R.id.TymButton);

        kiemTraTrangThaiYeuThich(currentFlight,tymBtn);
            tymBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tymBtn.isChecked()) {
                        // Đã được đánh dấu là yêu thích, bạn có thể thực hiện lưu lên Firestore
                        lưuDulieuLenFirestore(currentFlight);
                        tymBtn.setBackgroundResource(R.drawable.heart_red_24);
                    } else {
                        // Bỏ đánh dấu yêu thích, bạn có thể xóa dữ liệu trên Firestore (nếu cần)
                        xoaDulieuTrenFirestore(currentFlight);
                        tymBtn.setBackgroundResource(R.drawable.heart_24);
                    }
                }
            });

//        firebase.getTenSanBayBySanBayId(currentFlight.getDiemDen(), new Firebase.getTenSanBayBySanBayIdCallback() {
//            @Override
//            public void onCallback(String tensanbay) {
//                holder.nameflight.setText(tensanbay);
//            }
//        });

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


    }

    @Override
    public int getItemCount() {
        return flightlist.size();
    }

        // set sự kiện khi click vào item tym
    public interface OnItemClickListener{
        void onItemClick(ChuyenBay chuyenBay);
        }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener (OnItemClickListener listener){
        this.onItemClickListener = listener;
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
    private void lưuDulieuLenFirestore(ChuyenBay chuyenBay) {
        // Lấy tham chiếu đến Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Lấy tham chiếu đến bảng dữ liệu chứa danh sách yêu thích
        CollectionReference yeuThichCollection = db.collection("YeuThich");

        // Tạo một tài liệu mới để lưu thông tin chuyến bay yêu thích
        // Ở đây, chúng ta giả sử mỗi chuyến bay có một ID duy nhất, bạn cần xác định cách tự tạo ID hoặc sử dụng một cách duy nhất để xác định chuyến bay
        Map<String, Object> yeuThichData = new HashMap<>();
        yeuThichData.put("DiemDen", chuyenBay.getDiemDen());
        yeuThichData.put("HinhAnh",chuyenBay.getHinhAnh());
        yeuThichData.put("DiemDi", chuyenBay.getDiemDi());
        yeuThichData.put("GioBatDau",chuyenBay.getGioBatDau());
        yeuThichData.put("NgayDi",chuyenBay.getNgayDi());
        yeuThichData.put("NgayVe",chuyenBay.getNgayVe());
        yeuThichData.put("SoLuongGheTrong",chuyenBay.getSoLuongGheTrong());
        yeuThichData.put("SoLuongGheVipTrong",chuyenBay.getSoLuongGheVipTrong());
        yeuThichData.put("TrangThai",chuyenBay.getTrangThai());


        // Lưu thông tin yêu thích vào Firestore
        yeuThichCollection
                .document(chuyenBay.getIdChuyenBay())
                .set(yeuThichData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Dữ liệu đã được lưu thành công
                        Toast.makeText(context, "Đã thêm vào danh sách yêu thích!", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Đã xảy ra lỗi trong quá trình lưu dữ liệu
                        Toast.makeText(context, "Lỗi khi thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void xoaDulieuTrenFirestore(ChuyenBay chuyenBay) {
        // Lấy tham chiếu đến Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Lấy tham chiếu đến bảng dữ liệu chứa danh sách yêu thích
        CollectionReference yeuThichCollection = db.collection("YeuThich");

        // Xóa thông tin yêu thích dựa vào ID của chuyến bay
        yeuThichCollection
                .document(chuyenBay.getIdChuyenBay()) // Chúng ta sử dụng ID của chuyến bay làm tên tài liệu
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Đã xóa khỏi danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Lỗi khi xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void kiemTraTrangThaiYeuThich(ChuyenBay currentFlight, CheckBox tymBtn) {
        // Lấy trạng thái yêu thích từ cơ sở dữ liệu (ví dụ, từ Firestore)
        // Nếu mục này đã được đánh dấu yêu thích (trường "yeuThich" có giá trị true), thiết lập màu sắc của nút "tym" thành màu đỏ
        if (currentFlight.isYeuThich()) {
            tymBtn.setChecked(true);
            tymBtn.setBackgroundResource(R.drawable.heart_red_24);
        } else {
            tymBtn.setChecked(false);
            tymBtn.setBackgroundResource(R.drawable.heart_24);
        }
    }
}
