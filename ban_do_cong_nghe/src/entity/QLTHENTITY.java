/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author MSI USER
 */
public class QLTHENTITY {
    private int maTH;
    private String tenTH, anhTH;

    public QLTHENTITY() {
    }

    public QLTHENTITY(int maTH, String tenTH, String anhTH) {
        this.maTH = maTH;
        this.tenTH = tenTH;
        this.anhTH = anhTH;
    }

    public int getMaTH() {
        return maTH;
    }

    public void setMaTH(int maTH) {
        this.maTH = maTH;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public String getAnhTH() {
        return anhTH;
    }

    public void setAnhTH(String anhTH) {
        this.anhTH = anhTH;
    }
    
    
}
