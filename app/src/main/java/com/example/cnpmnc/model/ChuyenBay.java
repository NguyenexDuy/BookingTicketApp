package com.example.cnpmnc.model;

import java.io.Serializable;

public class ChuyenBay implements Serializable {
    private String  IdChuyenBay;
    private String DiemDen;
    private String DiemDi;
    private String GioBatDau;
    private String HinhAnh;
    private String NgayDi;
    private String NgayVe;
    private String SoLuongGheTrong;
    private String SoLuongGheVipTrong;
    private String TrangThai;
    private  String MoTa;
    private  String MoTaDiemDap;

    private boolean isFavorite;



    public ChuyenBay(){

    }
    private boolean isYeuThich;

    public boolean isYeuThich() {
        return isYeuThich;
    }




    public void setYeuThich(boolean yeuThich) {
        isYeuThich = yeuThich;
    }

    public ChuyenBay(String idChuyenBay, String diemDen, String diemDi, String gioBatDau, String hinhAnh, String ngayDi, String ngayVe, String soLuongGheTrong, String soLuongGheVipTrong, String trangThai, String moTa, String moTaDiemDap, boolean isYeuThich) {
        IdChuyenBay = idChuyenBay;
        DiemDen = diemDen;
        DiemDi = diemDi;
        GioBatDau = gioBatDau;
        HinhAnh = hinhAnh;
        NgayDi = ngayDi;
        NgayVe = ngayVe;
        SoLuongGheTrong = soLuongGheTrong;
        SoLuongGheVipTrong = soLuongGheVipTrong;
        TrangThai = trangThai;
        MoTa = moTa;
        MoTaDiemDap = moTaDiemDap;
        this.isYeuThich = isYeuThich;
    }
    public ChuyenBay(String idChuyenBay, String diemDen, String diemDi, String gioBatDau, String hinhAnh, String ngayDi, String ngayVe,
                     String soLuongGheTrong, String soLuongGheVipTrong, String trangThai, String moTa, String moTaDiemDap) {
        IdChuyenBay = idChuyenBay;
        DiemDen = diemDen;
        DiemDi = diemDi;
        GioBatDau = gioBatDau;
        HinhAnh = hinhAnh;
        NgayDi = ngayDi;
        NgayVe = ngayVe;
        SoLuongGheTrong = soLuongGheTrong;
        SoLuongGheVipTrong = soLuongGheVipTrong;
        TrangThai = trangThai;
        MoTa=moTa;
        MoTaDiemDap=moTaDiemDap;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getMoTaDiemDap() {
        return MoTaDiemDap;
    }

    public void setMoTaDiemDap(String moTaDiemDap) {
        MoTaDiemDap = moTaDiemDap;
    }

    public String getIdChuyenBay() {
        return IdChuyenBay;
    }

    public void setIdChuyenBay(String idChuyenBay) {
        IdChuyenBay = idChuyenBay;
    }

    public String getDiemDen() {
        return DiemDen;
    }

    public void setDiemDen(String diemDen) {
        DiemDen = diemDen;
    }

    public String getDiemDi() {
        return DiemDi;
    }

    public void setDiemDi(String diemDi) {
        DiemDi = diemDi;
    }

    public String getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        GioBatDau = gioBatDau;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getNgayDi() {
        return NgayDi;
    }

    public void setNgayDi(String ngayDi) {
        NgayDi = ngayDi;
    }

    public String getNgayVe() {
        return NgayVe;
    }

    public void setNgayVe(String ngayVe) {
        NgayVe = ngayVe;
    }

    public String getSoLuongGheTrong() {
        return SoLuongGheTrong;
    }

    public void setSoLuongGheTrong(String soLuongGheTrong) {
        SoLuongGheTrong = soLuongGheTrong;
    }

    public String getSoLuongGheVipTrong() {
        return SoLuongGheVipTrong;
    }

    public void setSoLuongGheVipTrong(String soLuongGheVipTrong) {
        SoLuongGheVipTrong = soLuongGheVipTrong;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }


}
