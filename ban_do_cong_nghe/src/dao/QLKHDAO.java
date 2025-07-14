/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.QLKHENTITY;
import java.util.*;
import java.sql.*;
import utils.ConnectDB;

/**
 *
 * @author dattr
 */
public class QLKHDAO {

    public List<QLKHENTITY> getAllKH() {
        List<QLKHENTITY> list = new ArrayList<>();
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "SELECT * FROM QLKH";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                QLKHENTITY kh = new QLKHENTITY(rs.getInt("maKH"), rs.getString("tenKH"), rs.getString("soDienThoai"), rs.getString("email"), rs.getString("diaChi"));
                list.add(kh);
            }
        } catch (Exception e) {
            System.out.println("Loi get all kh" + e.getMessage());
        }
        return list;
    }

    public void insertKH(QLKHENTITY kh) {
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "INSERT INTO QLKH (tenKH, soDienThoai, email, diaChi) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, kh.getTenKH());
            statement.setString(2, kh.getSoDienThoai());
            statement.setString(3, kh.getEmail());
            statement.setString(4, kh.getDiaChi());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Loi insert kh" + e.getMessage());
        }
    }

        public void updateKH(QLKHENTITY kh) {
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "UPDATE QLKH SET tenKH = ?, soDienThoai = ?, email = ?, diaChi = ? WHERE maKH = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, kh.getTenKH());
            statement.setString(2, kh.getSoDienThoai());
            statement.setString(3, kh.getEmail());
            statement.setString(4, kh.getDiaChi());
            statement.setInt(5, kh.getMaKh());
            statement.execute();
        } catch (Exception e) {
            System.out.println("Loi update kh" + e.getMessage());
        }
    }
    
    public void deleteKH(int maKH) {
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "DELETE FROM QLKH WHERE maKH = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, maKH);
            statement.execute();
        } catch (Exception e) {
            System.out.println("Loi delete KH" + e.getMessage());
        }
    }
}
