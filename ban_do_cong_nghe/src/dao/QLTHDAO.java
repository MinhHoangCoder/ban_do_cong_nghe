/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.QLTHENTITY;
import java.sql.*;
import java.util.*;
import utils.ConnectDB;

/**
 *
 * @author MSI USER
 */
public class QLTHDAO {
    public List<QLTHENTITY> getAllTH(){
        List<QLTHENTITY> Lst = new ArrayList<>();
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "SELECT * FROM ThuongHieu";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                QLTHENTITY th = new QLTHENTITY(
                        rs.getInt("maTH"), 
                        rs.getString("tenTH"));
                Lst.add(th);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Lst;
    }
    
    public void insertTH(QLTHENTITY th){
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "INSERT INTO ThuongHieu "
                    + "(tenTH) "
                    + "VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, th.getTenTH());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
            
    public void updateTH(QLTHENTITY th){
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "UPDATE ThuongHieu SET "
                    + "tenTH = ? "
                    + "WHERE maTH = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, th.getTenTH());
            ps.setInt(2, th.getMaTH());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteTH(int maTH){
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "DELETE FROM ThuongHieu WHERE maTH = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maTH);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
