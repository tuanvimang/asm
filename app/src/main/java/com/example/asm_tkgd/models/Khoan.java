package com.example.asm_tkgd.models;

public class Khoan {
    private int idKhoan;
    private String khoan;
    private int tien;
    private String ngay;
    private int idLoai;
    private String tenLoai;
// hiển thị
    public Khoan(int idKhoan, String khoan, int tien, String ngay, int idLoai, String tenLoai) {
        this.idKhoan = idKhoan;
        this.khoan = khoan;
        this.tien = tien;
        this.ngay = ngay;
        this.idLoai = idLoai;
        this.tenLoai = tenLoai;
    }
// thêm
    public Khoan(String khoan, int tien, String ngay, int idLoai) {
        this.khoan = khoan;
        this.tien = tien;
        this.ngay = ngay;
        this.idLoai = idLoai;
    }
// cập nhật
    public Khoan(int idKhoan, String khoan, int tien, String ngay, int idLoai) {
        this.idKhoan = idKhoan;
        this.khoan = khoan;
        this.tien = tien;
        this.ngay = ngay;
        this.idLoai = idLoai;
    }

    public int getIdKhoan() {
        return idKhoan;
    }

    public void setIdKhoan(int idKhoan) {
        this.idKhoan = idKhoan;
    }

    public String getKhoan() {
        return khoan;
    }

    public void setKhoan(String khoan) {
        this.khoan = khoan;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
