package com.example.cnpmnc.model;

public class SanBay {
    private String IdSanBay;
    private String TenSanBay;

    public SanBay(String idsSanBay, String tenSanBay) {
        IdSanBay = idsSanBay;
        TenSanBay = tenSanBay;
    }

    public String getIdSanBay() {
        return IdSanBay;
    }

    public void setIdSanBay(String idSanBay) {
        IdSanBay = idSanBay;
    }

    public String getTenSanBay() {
        return TenSanBay;
    }

    public void setTenSanBay(String tenSanBay) {
        TenSanBay = tenSanBay;
    }
}
