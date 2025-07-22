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
import java.sql.SQLException;

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
                
                QLNVENTITY nv = new QLNVENTITY(
                        rs.getInt("maNV"), 
                        rs.getInt("quyen"), 
                        rs.getString("tenNV"), 
                        rs.getString("soDienThoai"), 
                        rs.getString("email"), 
                        rs.getString("diaChi"), 
                        rs.getString("matKhau"), 
                        ngaySinh);
                Lst.add(nv);
            }
        } catch (SQLException e) {
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
            ps.setInt(7, nv.getQuyen());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            ps.setInt(7, nv.getQuyen());
            ps.setInt(8, nv.getMaNV());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteNV(int maNV){
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "DELETE FROM QLNV where maNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maNV);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public QLNVENTITY getUserByEmailOrPhone(String username) {
        String sql = "SELECT * FROM QLNV WHERE email = ? OR soDienThoai = ?";
        QLNVENTITY user = null;

        try (Connection con = ConnectDB.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Date sqlDate = rs.getDate("ngaySinh");
                LocalDate ngaySinh = null;
                if (sqlDate != null) {
                    ngaySinh = sqlDate.toLocalDate();
                }
                user = new QLNVENTITY(
                        rs.getInt("maNV"), 
                        rs.getInt("quyen"), 
                        rs.getString("tenNV"), 
                        rs.getString("soDienThoai"), 
                        rs.getString("email"), 
                        rs.getString("diaChi"), 
                        rs.getString("matKhau"), 
                        ngaySinh
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public void updatePassword(String PW, QLNVENTITY user){
        String sql = "UPDATE QLNV SET "
                + "matKhau = ? "
                + "WHERE email = ?";
        try(Connection con = ConnectDB.getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, PW);
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
