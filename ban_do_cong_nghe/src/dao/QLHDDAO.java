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
import java.sql.SQLException;

import utils.ConnectDB;
import entity.QLHDENTITY;

/**
 *
 * @author MSI USER
 */
public class QLHDDAO {
    public List<QLHDENTITY> getAllHD(){
        List<QLHDENTITY> Lst = new ArrayList<>();
        String sql = "select * from QLHD";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                Date sqlDate = rs.getDate("ngayLap");
                LocalDate ngayLap = null;
                if (sqlDate != null) {
                    ngayLap = sqlDate.toLocalDate();
                }
                QLHDENTITY hd = new QLHDENTITY(
                        rs.getInt("maHD"), 
                        rs.getInt("maNV"), 
                        rs.getInt("maKH"), 
                        rs.getInt("hinhThucThanhToan"), 
                        rs.getInt("trangThai"), 
                        ngayLap, 
                        rs.getFloat("tongTien"), 
                        rs.getString("liDoHuy"), 
                        rs.getString("ghiChu"));
                Lst.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Lst;
    }
    
    public List<QLHDENTITY> getAllHDCho(){
        List<QLHDENTITY> Lst = new ArrayList<>();
        String sql = "select * from QLHD where trangThai = 0";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                Date sqlDate = rs.getDate("ngayLap");
                LocalDate ngayLap = null;
                if (sqlDate != null) {
                    ngayLap = sqlDate.toLocalDate();
                }
                QLHDENTITY hd = new QLHDENTITY(
                        rs.getInt("maHD"), 
                        rs.getInt("maNV"), 
                        rs.getInt("maKH"), 
                        rs.getInt("hinhThucThanhToan"), 
                        rs.getInt("trangThai"), 
                        ngayLap, 
                        rs.getFloat("tongTien"), 
                        rs.getString("liDoHuy"), 
                        rs.getString("ghiChu"));
                Lst.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Lst;
    }
    
    public void insertHD (int maNV){
        String sql = "insert into QLHD "
                + "(maNV) values"
                + "(?)";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maNV);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Loi insert HD" + e.getMessage());
        }
    }
    
    public void huyHD (int maHD){
        String sql = "update QLHD set trangThai = 2 where maHD = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maHD);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi huy HD" + e.getMessage());
        }
    }
    
    public void thanhToanHD (int maHD){
        String sql = "update QLHD set trangThai = 1 where maHD = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maHD);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi thanh toan HD" + e.getMessage());
        }
    }
    
    public QLHDENTITY getOneHD(int id){
        String sql = "select * from QLHD where maHD = ?";
        try(Connection con = ConnectDB.getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Date sqlDate = rs.getDate("ngayLap");
                LocalDate ngayLap = null;
                if (sqlDate != null) {
                    ngayLap = sqlDate.toLocalDate();
                }
                QLHDENTITY hd = new QLHDENTITY(
                        rs.getInt("maHD"), 
                        rs.getInt("maNV"), 
                        rs.getInt("maKH"), 
                        rs.getInt("hinhThucThanhToan"), 
                        rs.getInt("trangThai"), 
                        ngayLap, 
                        rs.getFloat("tongTien"), 
                        rs.getString("liDoHuy"), 
                        rs.getString("ghiChu"));
                return hd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void insertKHIntoHD(int maKH, int maHD){
        String sql = "update QLHD set maKH = ? where maHD = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maKH);
            ps.setInt(2, maHD);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi insertKHIntoHD" + e.getMessage());
        }
    }
    
    public void updateHD(QLHDENTITY hd){
        String sql = "update QLHD set "
                + "tongTien = ?, "
                + "hinhThucThanhToan = ?, "
                + "ghiChu = ? "
                + "where maHD = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setFloat(1, hd.getTongTien());
            ps.setInt(2, hd.gethTTT());
            ps.setString(3, hd.getGhiChu());
            ps.setInt(4, hd.getMaHD());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
