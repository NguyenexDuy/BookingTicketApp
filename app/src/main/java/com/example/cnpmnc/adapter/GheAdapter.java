package com.example.cnpmnc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.Ghe;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class GheAdapter extends RecyclerView.Adapter<GheAdapter.GheViewHolder>  {
    private Context context;
    private ArrayList<Ghe> ghes;
    int maxSelect;
    private int currentSelections = 0;
    public interface OnGetGheInfoListener {
        void onGetGheInfoSuccess(long soGhe, String loaiGhe);
        void onGetGheInfoFailure();
    }
    public interface OnSeatSelectedListener {
        void onSeatSelected( long seatNumber, String loaiGhe);
    }

    private OnSeatSelectedListener mListener;

    public void setOnSeatSelectedListener(OnSeatSelectedListener listener) {
        mListener = listener;
    }

    public GheAdapter(Context context, ArrayList<Ghe> ghes,int maxSelect) {
        this.context = context;
        this.ghes = ghes;
        this.maxSelect=maxSelect;
    }

    @NonNull
    @Override
    public GheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chonchongoi, parent, false);
        return new GheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GheViewHolder holder, int position) {
        Ghe ghe=ghes.get(position);
        holder.checkBox.setChecked(ghe.getState());
        holder.checkBox.setTag(ghe.getLoaiGhe());
        holder.checkBox.setOnClickListener(view -> {
            getGheInfoFromFirestore(ghe.getIdGhe(), new OnGetGheInfoListener() {
                @Override
                public void onGetGheInfoSuccess(long soGhe, String loaiGhe) {
                    if (loaiGhe != null) {
                        Toast.makeText(context, "Số ghế: " + soGhe + ", Loại ghế: " + loaiGhe, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Không thể lấy thông tin loại ghế", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onGetGheInfoFailure() {
                    Toast.makeText(context, "Không thể lấy thông tin ghế tương ứng", Toast.LENGTH_SHORT).show();
                }
            });

            if (holder.checkBox.isChecked()) {
                if (currentSelections < maxSelect) {
                    ghe.setState(true);
                    updateFirestoreState(ghe);
                    currentSelections++;
                    if (mListener != null) {
                        mListener.onSeatSelected(ghe.getSoGhe(),ghe.getLoaiGhe());
                        Toast.makeText(context, String.valueOf(ghe.getSoGhe()), Toast.LENGTH_SHORT).show(); // Gửi vị trí khi ghế được chọn
                    }
                } else {
                    holder.checkBox.setChecked(false);
                    Toast.makeText(context, "Đã đủ số ghế", Toast.LENGTH_SHORT).show();
                }
            } else {
                ghe.setState(false);
                updateFirestoreState(ghe);
                currentSelections--;
            }
        });


    }

    private void updateFirestoreState(Ghe ghe) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentReference=db.collection("ghe").document(ghe.getIdGhe());
        documentReference.update("state",ghe.getState())
                .addOnSuccessListener(runnable -> {
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public int getItemCount() {
        return ghes.size();
    }

    public static class GheViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;

        public GheViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox);
        }
    }
    private void getGheInfoFromFirestore(String idGhe, OnGetGheInfoListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("ghe").document(idGhe);
        documentReference.get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        long soGhe = documentSnapshot.getLong("soGhe");
                        String loaiGhe = documentSnapshot.getString("loaighe");
                        if (listener != null) {
                            listener.onGetGheInfoSuccess(soGhe, loaiGhe);
                        }
                    } else {
                        // Không tìm thấy tài liệu
                        if (listener != null) {
                            listener.onGetGheInfoFailure();
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    if (listener != null) {
                        listener.onGetGheInfoFailure();
                    }
                });
    }
    public String getCurrentSeatType() {
        if (!ghes.isEmpty()) {
            String firstSeatType = ghes.get(0).getLoaiGhe();
            for (Ghe ghe : ghes) {
                if (!ghe.getLoaiGhe().equals(firstSeatType)) {
                    return "Có 2 loại ghế !!";
                }
            }

            return firstSeatType;
        }
        return "";
    }

}
