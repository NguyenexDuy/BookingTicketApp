package com.example.cnpmnc.model;

import java.io.PrintStream;
import java.io.Serializable;

public class HoaDon implements Serializable {
    private String IdHoaDon;
    private String DiemDi;
    private String DiemDen;
    private String SoLuongNguoiLon;
    private String SoLuongTreEm2Ttoi12T;
    private String SoLuongTreEmDuoi2T;

    public HoaDon(String diemDi, String diemDen, String soLuongNguoiLon, String soLuongTreEm2Ttoi12T, String soLuongTreEmDuoi2T) {
        DiemDi = diemDi;
        DiemDen = diemDen;
        SoLuongNguoiLon = soLuongNguoiLon;
        SoLuongTreEm2Ttoi12T = soLuongTreEm2Ttoi12T;
        SoLuongTreEmDuoi2T = soLuongTreEmDuoi2T;
    }

    public String getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        IdHoaDon = idHoaDon;
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
}
