package com.example.cnpmnc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class VeMayBay implements Serializable {

    String idVe;
    String idChuyenBay;
    String idHangKhach;
    String diemDiVe;
    String diemDenVe;
    String ngayBatDau;
    String ngayVe;
    String giaVe;
    String gioDi;
    String gioVe;
    Boolean canceled;

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public String getGioDi() {
        return gioDi;
    }

    public void setGioDi(String gioDi) {
        this.gioDi = gioDi;
    }

    public String getGioVe() {
        return gioVe;
    }

    public void setGioVe(String gioVe) {
        this.gioVe = gioVe;
    }

    public ArrayList<Map<String, Object>> getHangKhaches() {
        return hangKhaches;
    }

    public void setHangKhaches(ArrayList<Map<String, Object>> hangKhaches) {
        this.hangKhaches = hangKhaches;
    }

    ArrayList<Map<String,Object>> hangKhaches;
    public VeMayBay(String idVe, String idChuyenBay, String idHangKhach, String diemDiVe, String diemDenVe,String giodi,String giove, String ngayBatDau,String ngayVe, String giaVe,Boolean canceled,ArrayList<Map<String,Object>> hangKhaches) {
        this.idVe = idVe;
        this.idChuyenBay = idChuyenBay;
        this.idHangKhach = idHangKhach;
        this.diemDiVe = diemDiVe;
        this.diemDenVe = diemDenVe;
        this.gioDi=giodi;
        this.gioVe=giove;
        this.ngayBatDau = ngayBatDau;
        this.ngayVe=ngayVe;
        this.giaVe = giaVe;
        this.canceled=canceled;
        this.hangKhaches=hangKhaches;
    }






        public String getIdVe() {
        return idVe;
    }

    public void setIdVe(String idVe) {
        this.idVe = idVe;
    }

    public String getDiemDiVe() {
        return diemDiVe;
    }

    public void setDiemDiVe(String diemDiVe) {
        this.diemDiVe = diemDiVe;
    }

    public String getDiemDenVe() {
        return diemDenVe;
    }

    public void setDiemDenVe(String diemDenVe) {
        this.diemDenVe = diemDenVe;
    }

    public String getIdChuyenBay() {
        return idChuyenBay;
    }

    public void setIdChuyenBay(String idChuyenBay) {
        this.idChuyenBay = idChuyenBay;
    }

    public String getIdHangKhach() {
        return idHangKhach;
    }

    public void setIdHangKhach(String idHangKhach) {
        this.idHangKhach = idHangKhach;
    }



    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayVe() {
        return ngayVe;
    }

    public void setNgayVe(String ngayVe) {
        this.ngayVe = ngayVe;
    }

    public String getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(String giaVe) {
        this.giaVe = giaVe;
    }
}
