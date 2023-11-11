package com.example.cnpmnc.model;

import java.util.ArrayList;

public class DiaDiem {
    private static DiaDiem instance;
    private DiaDiem(){

    }
    public void reset() {
        if (HangKhachNguoiLonList != null) {
            HangKhachNguoiLonList.clear();
            HangKhachNguoiLonList = null;
        }

        SoLuongNguoiLon = null;
        SoLuongTreEm2Ttoi12T = null;
        SoLuongTreEmDuoi2T = null;
        NgayDi = null;
        NgayVe = null;
        DiemDi = null;
        DiemDen = null;
    }
    private ArrayList<HangKhach> HangKhachNguoiLonList;
    private ArrayList<HangKhach> HangKhachTreEm2_12TList;

    public ArrayList<HangKhach> getHangKhachNguoiLonList() {
        if (HangKhachNguoiLonList == null || HangKhachNguoiLonList.isEmpty()) {
            HangKhachNguoiLonList = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                HangKhach defaultHangKhach = new HangKhach("Người lớn", "abc","","","","");
                HangKhachNguoiLonList.add(defaultHangKhach);
            }
        }
        return HangKhachNguoiLonList;
    }
    public ArrayList<HangKhach> getHangKhachTreEm2_12TList() {
        if(HangKhachTreEm2_12TList ==null|| HangKhachTreEm2_12TList.isEmpty())
        {
            HangKhachTreEm2_12TList =new ArrayList<>();
            for(int i=0;i<4;i++)
            {
                HangKhach hangkhachTreEm=new HangKhach("Trẻ em","Tên khách hàng","");
                HangKhachTreEm2_12TList.add(hangkhachTreEm);
            }
        }
        return HangKhachTreEm2_12TList;
    }

    public void setHangKhachTreEm2_12TList(ArrayList<HangKhach> hangKhachTreEm2_12TList) {
        HangKhachTreEm2_12TList = hangKhachTreEm2_12TList;
    }

    public void setHangKhachNguoiLonList(ArrayList<HangKhach> hangKhachList) {
        HangKhachNguoiLonList = hangKhachList;
    }

    private String SoLuongNguoiLon;
    private String SoLuongTreEm2Ttoi12T;
    private String SoLuongTreEmDuoi2T;
    private String NgayDi,NgayVe;

    public String getNgayVe() {
        return NgayVe;
    }

    public void setNgayVe(String ngayVe) {
        NgayVe = ngayVe;
    }

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
