package com.example.qlnv.model;

public class NhanVien {
    private int id;
    private String ten;
    private int tuoi;
    private int gioiTinh;
    private String chuyenMon;
    private int phongId;

    public NhanVien(String ten, int tuoi, int gioiTinh, String chuyenMon, int phongId) {
        this.ten = ten;
        this.tuoi = tuoi;
        this.gioiTinh = gioiTinh;
        this.chuyenMon = chuyenMon;
        this.phongId = phongId;
    }

    public NhanVien(int id, String ten, int tuoi, int gioiTinh, String chuyenMon, int phongId) {
        this.id = id;
        this.ten = ten;
        this.tuoi = tuoi;
        this.gioiTinh = gioiTinh;
        this.chuyenMon = chuyenMon;
        this.phongId = phongId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getChuyenMon() {
        return chuyenMon;
    }

    public void setChuyenMon(String chuyenMon) {
        this.chuyenMon = chuyenMon;
    }

    public int getPhongId() {
        return phongId;
    }

    public void setPhongId(int phongId) {
        this.phongId = phongId;
    }
}
