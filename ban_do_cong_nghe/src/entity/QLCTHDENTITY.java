/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author MSI USER
 */
public class QLCTHDENTITY {
    int maCTHD, maHD, maSP, soLuong;
    float gia;
    String tenSP;

    public QLCTHDENTITY() {
    }

    public QLCTHDENTITY(int maCTHD, int maHD, int maSP, int soLuong, float gia, String tenSP) {
        this.maCTHD = maCTHD;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tenSP = tenSP;
    }

    public QLCTHDENTITY(int maHD, int maSP, int soLuong, float gia, String tenSP) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tenSP = tenSP;
    }

    public int getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(int maCTHD) {
        this.maCTHD = maCTHD;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    
    
}
