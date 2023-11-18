package com.example.cnpmnc.adapter;

import android.content.Context;
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
    public interface OnSeatSelectedListener {
        void onSeatSelected( long seatNumber);
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
        holder.checkBox.setOnClickListener(view -> {
            if (holder.checkBox.isChecked()) {
                if(currentSelections<maxSelect)
                {
                    ghe.setState(true);
                    updateFirestoreState(ghe);
                    currentSelections++;
                    if (mListener != null) {
                        mListener.onSeatSelected( ghe.getSoGhe());
                        Toast.makeText(context, String.valueOf(ghe.getSoGhe()), Toast.LENGTH_SHORT).show();// Gửi vị trí khi ghế được chọn
                    }
                }
                else {
                    holder.checkBox.setChecked(false);
                    Toast.makeText(context, "Đã đủ số ghế", Toast.LENGTH_SHORT).show();
                }

            }else {
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
}
