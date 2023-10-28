package com.example.cnpmnc.adapter;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cnpmnc.fragment.KhuHoiFragment;
import com.example.cnpmnc.fragment.MotChieuFragment;
import com.example.cnpmnc.fragment.NhieuChangFragment;
import com.example.cnpmnc.model.ChuyenBay;

public class VPBookingAdapter extends FragmentStatePagerAdapter {
    private ChuyenBay chuyenBay;
    private String DiemDi,DiemDen;
    public VPBookingAdapter(@NonNull FragmentManager fm, int behavior, ChuyenBay chuyenBay) {
        super(fm, behavior);
        this.chuyenBay = chuyenBay;
    }
    public VPBookingAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    public VPBookingAdapter(@NonNull FragmentManager fm, int behavior, String diemdi, String diemden) {
        super(fm, behavior);
        this.DiemDi = diemdi;
        this.DiemDen = diemden;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (chuyenBay != null){
            switch (position){
                case 0:
                    return new KhuHoiFragment(chuyenBay);
                case 1:
                    return new MotChieuFragment(chuyenBay);
                case 2:
                    return new NhieuChangFragment();
                default:
                    return new MotChieuFragment();
            }
        }else  if (DiemDi != null){
            switch (position) {
                case 0:
                    return new KhuHoiFragment(DiemDi, null);
                case 1:
                    return new MotChieuFragment(DiemDi, null);
                case 2:
                    return new NhieuChangFragment();
                default:
                    return new MotChieuFragment();
            }
        }
        else {
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
