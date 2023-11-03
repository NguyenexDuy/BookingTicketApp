package com.example.cnpmnc.model;

public class HangKhach {
    private String HoTen;
    private String Tuoi;

    public HangKhach(String hoTen, String tuoi) {
        HoTen = hoTen;
        Tuoi = tuoi;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getTuoi() {
        return Tuoi;
    }

    public void setTuoi(String tuoi) {
        Tuoi = tuoi;
    }
}
