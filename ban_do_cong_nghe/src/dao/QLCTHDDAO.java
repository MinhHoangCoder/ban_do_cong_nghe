/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.QLCTHDENTITY;
import java.util.*;
import java.sql.*;
import utils.ConnectDB;
/**
 *
 * @author MSI USER
 */
public class QLCTHDDAO {
    public void insertCTHD(QLCTHDENTITY cthd) {
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "insert into QLCTHD (maHD, maSP, tenSP, soLuong, gia)"
                    + "values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cthd.getMaHD());
            stmt.setInt(2, cthd.getMaSP());
            stmt.setString(3, cthd.getTenSP());
            stmt.setInt(4, cthd.getSoLuong());
            stmt.setFloat(5, cthd.getGia());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Co loi insert cthddao: " + e.getMessage());
        }
    }
    
    public List<QLCTHDENTITY> getAllByMaHD(int maHD) {
        List<QLCTHDENTITY> list = new ArrayList();
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "select * from QLCTHD where maHD = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, maHD);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                QLCTHDENTITY cthd = new QLCTHDENTITY(result.getInt("maCTHD"), 
                        result.getInt("maHD"), result.getInt("maSP"), result.getInt("soLuong"),
                        result.getFloat("gia"), result.getString("tenSP"));
                list.add(cthd);
            }
        } catch (Exception e) {
            System.out.println("Co loi getall cthddao: " + e.getMessage());
        }
        return list;
    }
    
    public boolean checkExistSPInCTHD(int maHD, int maSP) {
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "select * from QLCTHD where maHD = ? and maSP = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, maHD);
            stmt.setInt(2, maSP);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Co loi checkExistSPInCTHD cthddao: " + e.getMessage());
        }
        return false;
    }
    
    public void increaseQuantityCTHD(int maHD, int maSP) {
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "update QLCTHD set soLuong = soLuong + 1 where maHD = ? and maSP = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, maHD);
            stmt.setInt(2, maSP);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Co loi increaseQuantityCTHD cthddao: " + e.getMessage());
        }
    }
    
    public Float getTotalMoneyByMaHD(int maHD){
        float tongTienHD = 1;
        String sql = "select sum(thanhTien) as tongTienHD from QLCTHD where maHD = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maHD);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                tongTienHD = rs.getFloat("tongTienHD");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tongTienHD;
    }
    
    public int getTotalItemsByMaHD(int maHD){
        int soLuongSP = 1;
        String sql = "select sum(soLuong) as soLuongSP from QLCTHD where maHD = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maHD);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                soLuongSP = rs.getInt("soLuongSP");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuongSP;
    }
}
