package com.example.cnpmnc.model;

public class DiaDiem {
    private static DiaDiem instance;
    private DiaDiem(){

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
