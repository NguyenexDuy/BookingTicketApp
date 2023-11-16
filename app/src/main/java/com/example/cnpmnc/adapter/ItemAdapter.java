package com.example.cnpmnc.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<String> itemList;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private OnSeatClickListener onSeatClickListener;
    public interface OnSeatClickListener {
        void onSeatClick(int position);
    }
    public ItemAdapter(ArrayList<String> itemList,OnSeatClickListener listener) {
        this.itemList = itemList;
        this.onSeatClickListener=listener;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chonchongoi, parent, false);
        return new ItemViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.checkBox.setChecked(position==selectedPosition);
        holder.checkBox.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();
            onSeatClickListener.onSeatClick(selectedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;
        CheckBox checkBox;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox);
        }

        void bind(String item) {
            textView1.setText(item);
            textView2.setText(item);
            textView3.setText(item);
        }
    }
}

