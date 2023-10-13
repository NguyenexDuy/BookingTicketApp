package com.example.cnpmnc.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cnpmnc.fragment.KhuHoiFragment;
import com.example.cnpmnc.fragment.MotChieuFragment;
import com.example.cnpmnc.fragment.NhieuChangFragment;

public class VPBookingAdapter extends FragmentStatePagerAdapter {
    public VPBookingAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new KhuHoiFragment();
            case 1:
                return new MotChieuFragment();
            case 2:
                return new NhieuChangFragment();
            default:
                return new KhuHoiFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Khứ hồi";
                break;
            case 1:
                title = "Một chiều";
                break;
            case 2:
                title = "Nhiều chặng";
                break;
        }
        return title;
    }
}
