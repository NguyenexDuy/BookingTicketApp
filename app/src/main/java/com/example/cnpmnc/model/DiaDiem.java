package com.example.cnpmnc.model;

public class DiaDiem {
    private static DiaDiem instance;
    private DiaDiem(){

    }
    private String SoLuongNguoiLon;
    private String SoLuongTreEm2Ttoi12T;
    private String SoLuongTreEmDuoi2T;
    private String NgayDi;

    public String getNgayDi() {
        return NgayDi;
    }

    public void setNgayDi(String ngayDi) {
        NgayDi = ngayDi;
    }

    public String getSoLuongNguoiLon() {
        return SoLuongNguoiLon;
    }

    public void setSoLuongNguoiLon(String soLuongNguoiLon) {
        SoLuongNguoiLon = soLuongNguoiLon;
    }

    public String getSoLuongTreEm2Ttoi12T() {
        return SoLuongTreEm2Ttoi12T;
    }

    public void setSoLuongTreEm2Ttoi12T(String soLuongTreEm2Ttoi12T) {
        SoLuongTreEm2Ttoi12T = soLuongTreEm2Ttoi12T;
    }

    public String getSoLuongTreEmDuoi2T() {
        return SoLuongTreEmDuoi2T;
    }

    public void setSoLuongTreEmDuoi2T(String soLuongTreEmDuoi2T) {
        SoLuongTreEmDuoi2T = soLuongTreEmDuoi2T;
    }
    private String DiemDi = null;
    private String DiemDen = null;

    public static void setInstance(DiaDiem instance) {
        DiaDiem.instance = instance;
    }

    public String getDiemDi() {
        return DiemDi;
    }

    public void setDiemDi(String diemDi) {
        DiemDi = diemDi;
    }

    public String getDiemDen() {
        return DiemDen;
    }

    public void setDiemDen(String diemDen) {
        DiemDen = diemDen;
    }

    public static synchronized DiaDiem getInstance(){
        if(instance == null){
            instance = new DiaDiem();
        }
        return instance;
    }
}
