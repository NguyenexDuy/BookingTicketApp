package com.example.cnpmnc.model;

import java.util.ArrayList;

public class HangKhachDataHolder {
    private static HangKhachDataHolder instance;
    private ArrayList<HangKhach> hangKhachList;

    private HangKhachDataHolder() {
        hangKhachList = new ArrayList<>();
    }

    public static synchronized HangKhachDataHolder getInstance() {
        if (instance == null) {
            instance = new HangKhachDataHolder();
        }
        return instance;
    }

    public void setHangKhachList(ArrayList<HangKhach> hangKhachList) {
        this.hangKhachList = hangKhachList;
    }

    public ArrayList<HangKhach> getHangKhachList() {
        return hangKhachList;
    }
}
