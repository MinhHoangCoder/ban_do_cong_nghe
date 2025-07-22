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
                        rs.getInt("maKH"), 
                        rs.getInt("maNV"), 
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
    
    
}
