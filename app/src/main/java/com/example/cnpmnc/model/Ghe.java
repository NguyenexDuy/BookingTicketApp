package com.example.cnpmnc.model;

public class Ghe {
    private String idGhe;
    private String idChuyenBay;
    private Boolean state;

    public Ghe(String idGhe, String idChuyenBay, Boolean state) {
        this.idGhe = idGhe;
        this.idChuyenBay = idChuyenBay;
        this.state = state;
    }

    public String getIdGhe() {
        return idGhe;
    }

    public void setIdGhe(String idGhe) {
        this.idGhe = idGhe;
    }

    public String getIdChuyenBay() {
        return idChuyenBay;
    }

    public void setIdChuyenBay(String idChuyenBay) {
        this.idChuyenBay = idChuyenBay;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
