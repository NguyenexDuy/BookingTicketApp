package com.example.cnpmnc.activity;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;

public class ItemTextViewAdapter {

    static class ItemTextViewViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ItemTextViewViewHolder(@NonNull View itemView) {
            super(itemView);
//            textView=itemView.findViewById(R.id.tvNumber);
        }
    }
}
