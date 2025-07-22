/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

/**
 *
 * @author MSI USER
 */
public class QLHDENTITY {
    private int maHD, maKH, maNV, hTTT, trangThaiHD;
    private LocalDate ngayLapHD;
    private float tongTien;
    private String liDoHuy, ghiChu;

    public QLHDENTITY() {
    }

    public QLHDENTITY(int maHD, int maKH, int maNV, int hTTT, int trangThaiHD, LocalDate ngayLapHD, float tongTien, String liDoHuy, String ghiChu) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.hTTT = hTTT;
        this.trangThaiHD = trangThaiHD;
        this.ngayLapHD = ngayLapHD;
        this.tongTien = tongTien;
        this.liDoHuy = liDoHuy;
        this.ghiChu = ghiChu;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int gethTTT() {
        return hTTT;
    }

    public void sethTTT(int hTTT) {
        this.hTTT = hTTT;
    }

    public int getTrangThaiHD() {
        return trangThaiHD;
    }

    public void setTrangThaiHD(int trangThaiHD) {
        this.trangThaiHD = trangThaiHD;
    }

    public LocalDate getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(LocalDate ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getLiDoHuy() {
        return liDoHuy;
    }

    public void setLiDoHuy(String liDoHuy) {
        this.liDoHuy = liDoHuy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
