/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author MSI USER
 */
public class QLSPENTITY {
    private int maSP, maDM, maTH, soLuongSP, trangThaiSP;
    private float giaSP;
    private String tenSP, moTa;

    public QLSPENTITY() {
    }

    public QLSPENTITY(int maSP, int maDM, int maTH, int soLuongSP, int trangThaiSP, float giaSP, String tenSP, String moTa) {
        this.maSP = maSP;
        this.maDM = maDM;
        this.maTH = maTH;
        this.soLuongSP = soLuongSP;
        this.trangThaiSP = trangThaiSP;
        this.giaSP = giaSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public int getMaTH() {
        return maTH;
    }

    public void setMaTH(int maTH) {
        this.maTH = maTH;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public int getTrangThaiSP() {
        return trangThaiSP;
    }

    public void setTrangThaiSP(int trangThaiSP) {
        this.trangThaiSP = trangThaiSP;
    }

    public float getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(float giaSP) {
        this.giaSP = giaSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    
}
