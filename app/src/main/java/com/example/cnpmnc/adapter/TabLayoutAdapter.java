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

    public void setChuyenBay(ChuyenBay chuyenBay) {
        this.chuyenBay = chuyenBay;
        notifyDataSetChanged(); // Gọi phương thức notifyDataSetChanged() ở đây sẽ không có tác dụng
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return LoaiVePhoThongFragment.newInstance(chuyenBay);
            case 1:
                return LoaiVeThuongGiaFragment.newInstance(chuyenBay);
            default:
                return LoaiVePhoThongFragment.newInstance(chuyenBay);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
