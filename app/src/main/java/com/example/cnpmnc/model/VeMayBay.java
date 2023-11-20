package com.example.cnpmnc.model;

public class VeMayBay {

    String idVe;
    String idChuyenBay;
    String idHangKhach;
    String diemDiVe;
    String diemDenVe;
    String ngayBatDau;
    String ngayVe;
    String giaVe;

    public VeMayBay(String idVe, String idChuyenBay, String idHangKhach, String diemDiVe, String diemDenVe, String ngayBatDau, String giaVe) {
        this.idVe = idVe;
        this.idChuyenBay = idChuyenBay;
        this.idHangKhach = idHangKhach;
        this.diemDiVe = diemDiVe;
        this.diemDenVe = diemDenVe;
        this.ngayBatDau = ngayBatDau;
        this.giaVe = giaVe;
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
