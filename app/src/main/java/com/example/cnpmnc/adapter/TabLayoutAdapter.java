package com.example.cnpmnc.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cnpmnc.fragment.LoaiVePhoThongFragment;
import com.example.cnpmnc.fragment.LoaiVeThuongGiaFragment;

public class TabLayoutAdapter extends FragmentStateAdapter {
    public TabLayoutAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public TabLayoutAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            return new LoaiVeThuongGiaFragment();
        }
        return new LoaiVePhoThongFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
