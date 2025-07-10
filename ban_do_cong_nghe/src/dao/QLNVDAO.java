/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;              
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utils.ConnectDB;
import entity.QLNVENTITY;
import entity.QuyenENTITY;

/**
 *
 * @author MSI USER
 */
public class QLNVDAO {
    public List<QLNVENTITY> getAllNV(){
        List<QLNVENTITY> Lst = new ArrayList<>();
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "SELECT * FROM QLNV";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Date sqlDate = rs.getDate("ngaySinh");
                LocalDate ngaySinh = null;
                if (sqlDate != null) {
                    ngaySinh = sqlDate.toLocalDate();
                }
                
                int maQ = rs.getInt("quyen");
                String tenQuyen = convertQuyenIntToString(maQ);
                
                QLNVENTITY nv = new QLNVENTITY(
                        rs.getInt("maNV"), 
                        tenQuyen, 
                        rs.getString("tenNV"), 
                        rs.getString("soDienThoai"), 
                        rs.getString("email"), 
                        rs.getString("diaChi"), 
                        rs.getString("matKhau"), 
                        ngaySinh);
                Lst.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Lst;
    }
    
    public void insertNV(QLNVENTITY nv){
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "INSERT INTO QLNV "
                   + "(tenNV, ngaySinh, soDienThoai, email, diaChi, matKhau, quyen) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getTenNV());
            ps.setDate(2, Date.valueOf(nv.getNgaySinh()));
            ps.setString(3, nv.getSoDienThoai());
            ps.setString(4, nv.getEmail());
            ps.setString(5, nv.getDiaChi());
            ps.setString(6, nv.getMatKhau());
            ps.setInt(7, this.convertQuyenStringToInt(nv.getQuyen()));
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int convertQuyenStringToInt(String tenQuyen){
        int maQuyen = 0;
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "SELECT maQ FROM quyen where tenQ = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenQuyen);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                maQuyen = rs.getInt("maQ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maQuyen;
    }
    
    public String convertQuyenIntToString(int maQuyen) {
        String tenQuyen = "";
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "SELECT tenQ FROM quyen WHERE maQ = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maQuyen);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tenQuyen = rs.getString("tenQ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenQuyen;
    }
    
    public void updateNV(QLNVENTITY nv){
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "UPDATE QLNV SET "
               + "tenNV = ?, "
               + "ngaySinh = ?, "
               + "soDienThoai = ?, "
               + "email = ?, "
               + "diaChi = ?, "
               + "matKhau = ?, "
               + "quyen = ? "
               + "WHERE maNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getTenNV());
            ps.setDate(2, Date.valueOf(nv.getNgaySinh()));
            ps.setString(3, nv.getSoDienThoai());
            ps.setString(4, nv.getEmail());
            ps.setString(5, nv.getDiaChi());
            ps.setString(6, nv.getMatKhau());
            ps.setInt(7, this.convertQuyenStringToInt(nv.getQuyen()));
            ps.setInt(8, nv.getMaNV());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteNV(int maNV){
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "DELETE FROM QLNV where maNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maNV);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<QuyenENTITY> getAllQuyen(){
        List<QuyenENTITY> Lst = new ArrayList<>();
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "SELECT * FROM quyen";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                QuyenENTITY q = new QuyenENTITY(rs.getInt("maQ"), rs.getString("tenQ"));
                Lst.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Lst;
    }
}
