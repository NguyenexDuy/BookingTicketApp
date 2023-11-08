package com.example.cnpmnc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.HomePageActivity;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.SanBay;

import java.util.ArrayList;
import java.util.List;


public class TimKiemDiemDiAdapter extends RecyclerView.Adapter<TimKiemDiemDiAdapter.ViewHolder> implements Filterable {

    private Context mcontext;
    private ArrayList<SanBay> msanBays;
    private ChuyenBay chuyenBay;
    public TimKiemDiemDiAdapter(Context context, ArrayList<SanBay> sanBays) {
        this.mcontext = context;
        this.msanBays = sanBays;
    }
    public TimKiemDiemDiAdapter(Context context, ArrayList<SanBay> sanBays, ChuyenBay chuyenbay) {
        this.mcontext = context;
        this.msanBays = sanBays;
        this.chuyenBay = chuyenbay;
    }

    @NonNull
    @Override
    public TimKiemDiemDiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_muctimkiem, parent, false);
        return new TimKiemDiemDiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimKiemDiemDiAdapter.ViewHolder holder, int position) {
        SanBay sanBay= msanBays.get(position);
        holder.tv_SanBay1.setText(sanBay.getTenSanBay());
        holder.tv_SanBay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chuyenBay!= null){
                    chuyenBay.setDiemDi(sanBay.getIdSanBay());
                    Intent i = new Intent(mcontext, HomePageActivity.class);
                    i.putExtra("Chuyenbay", chuyenBay);
                    mcontext.startActivity(i);
                }else {
                    Intent i = new Intent(mcontext, HomePageActivity.class);
                    i.putExtra("DiemDi", sanBay.getIdSanBay());
                    mcontext.startActivity(i);
                }
            }
        });
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchText = charSequence.toString().toLowerCase();
                List<SanBay> filteredList = new ArrayList<>();
                if (searchText.isEmpty()) {
                    filteredList.addAll(msanBays);
                } else {
                    for (SanBay sanBay : msanBays) {
                        if (sanBay.getTenSanBay().toLowerCase().contains(searchText)) {
                            filteredList.add(sanBay);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                msanBays.clear();
                msanBays.addAll((List<SanBay>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
    public void setData(ArrayList<SanBay> filteredList) {
        this.msanBays = filteredList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return msanBays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_SanBay1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_SanBay1=itemView.findViewById(R.id.tv_SanBay1);
        }
    }
}
