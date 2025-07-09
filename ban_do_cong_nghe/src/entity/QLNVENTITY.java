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
public class QLNVENTITY {
    private int maNV;
    private String tenNV, soDienThoai, email, diaChi, matKhau, quyen;
    private LocalDate ngaySinh;

    public QLNVENTITY() {
    }

    public QLNVENTITY(String quyen, String tenNV, String soDienThoai, String email, String diaChi, String matKhau, LocalDate ngaySinh) {
        this.quyen = quyen;
        this.tenNV = tenNV;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
    }

    public QLNVENTITY(int maNV, String quyen, String tenNV, String soDienThoai, String email, String diaChi, String matKhau, LocalDate ngaySinh) {
        this.maNV = maNV;
        this.quyen = quyen;
        this.tenNV = tenNV;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    
}
