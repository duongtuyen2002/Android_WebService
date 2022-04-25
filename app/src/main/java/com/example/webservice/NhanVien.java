package com.example.webservice;

import java.io.Serializable;

public class NhanVien implements Serializable {
    int maNV;
    private String tenNV, SDT;
    public NhanVien(int maNV, String tenNV, String SDT) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.SDT = SDT;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

}
