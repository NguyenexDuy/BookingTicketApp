package com.example.cnpmnc.adapter;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cnpmnc.fragment.LoaiVePhoThongFragment;
import com.example.cnpmnc.fragment.LoaiVeThuongGiaFragment;
import com.example.cnpmnc.model.ChuyenBay;

import java.util.List;

public class TabLayoutAdapter extends FragmentStateAdapter {

    private ChuyenBay chuyenBay;

    public TabLayoutAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public TabLayoutAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoaiVePhoThongFragment();
            case 1:
                return new LoaiVeThuongGiaFragment();
            default:
                return new LoaiVePhoThongFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
