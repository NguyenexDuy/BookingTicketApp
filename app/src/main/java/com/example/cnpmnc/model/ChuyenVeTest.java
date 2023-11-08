package com.example.cnpmnc.model;


public class ChuyenVeTest {
    private String maChuyenBay;
    private String ngayBay;
    private String ngayDen;
    private String gioBay;
    private String gioDen;
    private String noiBay;
    private String noiDen;
    private String tongTien;

    public ChuyenVeTest(String maChuyenBay, String ngayBay, String ngayDen, String gioBay, String gioDen, String noiBay, String noiDen, String tongTien) {
        this.maChuyenBay = maChuyenBay;
        this.ngayBay = ngayBay;
        this.ngayDen = ngayDen;
        this.gioBay = gioBay;
        this.gioDen = gioDen;
        this.noiBay = noiBay;
        this.noiDen = noiDen;
        this.tongTien = tongTien;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getNgayBay() {
        return ngayBay;
    }

    public void setNgayBay(String ngayBay) {
        this.ngayBay = ngayBay;
    }

    public String getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(String ngayDen) {
        this.ngayDen = ngayDen;
    }

    public String getGioBay() {
        return gioBay;
    }

    public void setGioBay(String gioBay) {
        this.gioBay = gioBay;
    }

    public String getGioDen() {
        return gioDen;
    }

    public void setGioDen(String gioDen) {
        this.gioDen = gioDen;
    }

    public String getNoiBay() {
        return noiBay;
    }

    public void setNoiBay(String noiBay) {
        this.noiBay = noiBay;
    }

    public String getNoiDen() {
        return noiDen;
    }

    public void setNoiDen(String noiDen) {
        this.noiDen = noiDen;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}

