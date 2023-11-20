package com.example.cnpmnc.model;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaDiem {
    private static DiaDiem instance;
    private DiaDiem(){

    }
    public void reset() {
        if (HangKhachNguoiLonList != null) {
            HangKhachNguoiLonList.clear();
            HangKhachNguoiLonList = null;
        }if(HangKhachTreEm2_12TList!=null){
            HangKhachTreEm2_12TList.clear();
            HangKhachTreEm2_12TList=null;
        }if(HangKhachTreEmDuoi2TList!=null){
            HangKhachTreEmDuoi2TList.clear();
            HangKhachTreEmDuoi2TList=null;
        }

        SoLuongNguoiLon = null;
        SoLuongTreEm2Ttoi12T = null;
        SoLuongTreEmDuoi2T = null;
        NgayDi = null;
        NgayVe = null;
        DiemDi = null;
        DiemDen = null;
    }
    private int selectedSeatPosition = -1;

    public int getSelectedSeatPosition() {
        return selectedSeatPosition;
    }

    public void setSelectedSeatPosition(int selectedSeatPosition) {
        this.selectedSeatPosition = selectedSeatPosition;
    }
    private ArrayList<HangKhach> HangKhachNguoiLonList;
    private ArrayList<HangKhach> HangKhachTreEm2_12TList;
    private ArrayList<HangKhach> HangKhachTreEmDuoi2TList;
    private ArrayList<HangKhach> AllHangKhach;


    public ArrayList<HangKhach> getHangKhachNguoiLonList() {
        if (HangKhachNguoiLonList == null || HangKhachNguoiLonList.isEmpty()) {
            HangKhachNguoiLonList = new ArrayList<>();

            for (int i = 0; i < Integer.parseInt(DiaDiem.getInstance().getSoLuongNguoiLon()) ; i++) {
                HangKhach defaultHangKhach = new HangKhach("Người lớn", "abc","","","","");
                HangKhachNguoiLonList.add(defaultHangKhach);
            }
        }
        return HangKhachNguoiLonList;
    }

    public ArrayList<HangKhach> getHangKhachTreEmDuoi2TList() {
        if(HangKhachTreEmDuoi2TList==null|| HangKhachTreEmDuoi2TList.isEmpty())
        {
            HangKhachTreEmDuoi2TList=new ArrayList<>();
            for (int i=0;i< Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEmDuoi2T());i++)
            {
                HangKhach hangKhachTreEmDuoi2T=new HangKhach("Trẻ em (dưới 2 tuổi)","Tên trẻ em","");
                HangKhachTreEmDuoi2TList.add(hangKhachTreEmDuoi2T);
            }
        }
        return HangKhachTreEmDuoi2TList;
    }
    public ArrayList<HangKhach> getHangKhachTreEm2_12TList() {
        if(HangKhachTreEm2_12TList ==null|| HangKhachTreEm2_12TList.isEmpty())
        {
            HangKhachTreEm2_12TList =new ArrayList<>();
            for(int i=0;i< Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEm2Ttoi12T());i++)
            {
                HangKhach hangkhachTreEm=new HangKhach("Trẻ em(2-12 tuổi)","Tên khách hàng","");
                HangKhachTreEm2_12TList.add(hangkhachTreEm);
            }
        }
        return HangKhachTreEm2_12TList;
    }

    public void setHangKhachTreEmDuoi2TList(ArrayList<HangKhach> hangKhachTreEmDuoi2TList) {
        HangKhachTreEmDuoi2TList = hangKhachTreEmDuoi2TList;
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
    private String NgayDi,NgayVe,SoLuongGheTrong;

    public String getSoLuongGheTrong() {
        return SoLuongGheTrong;
    }

    public void setSoLuongGheTrong(String soLuongGheTrong) {
        SoLuongGheTrong = soLuongGheTrong;
    }

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
    private String IdChuyenBay=null;
    private String GiaVe=null;

    public String getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(String giaVe) {
        GiaVe = giaVe;
    }



    public String getIdChuyenBay() {
        return IdChuyenBay;
    }

    public void setIdChuyenBay(String idChuyenBay) {
        IdChuyenBay = idChuyenBay;
    }

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
