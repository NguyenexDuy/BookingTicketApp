package com.example.cnpmnc.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<String> itemList;

    public ItemAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chonchongoi, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String item = itemList.get(position);
//        holder.bind(item);
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
//            textView1 = itemView.findViewById(R.id.textView1);
//            textView2 = itemView.findViewById(R.id.textView2);
//            textView3 = itemView.findViewById(R.id.textView3);
            checkBox=itemView.findViewById(R.id.checkbox);
        }

        void bind(String item) {
            textView1.setText(item);
            textView2.setText(item);
            textView3.setText(item);
        }
    }
}

