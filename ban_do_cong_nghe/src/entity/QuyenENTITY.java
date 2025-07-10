/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author MSI USER
 */
public class QuyenENTITY {
    private int maQ;
    private String tenQ; 
    

    public QuyenENTITY() {
    }

    public QuyenENTITY(int maQ, String tenQ) {
        this.maQ = maQ;
        this.tenQ = tenQ;
    }

    public int getMaQ() {
        return maQ;
    }

    public void setMaQ(int maQ) {
        this.maQ = maQ;
    }

    public String getTenQ() {
        return tenQ;
    }

    public void setTenQ(String tenQ) {
        this.tenQ = tenQ;
    }

}
