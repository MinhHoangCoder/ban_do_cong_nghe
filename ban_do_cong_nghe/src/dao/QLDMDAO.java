/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.QLDMENTITY;
import java.sql.*;
import java.util.*;
import utils.ConnectDB;
/**
 *
 * @author MSI USER
 */
public class QLDMDAO {
    public List<QLDMENTITY> getAllDM(){
        List<QLDMENTITY> Lst = new ArrayList<>();
        String sql = "SELECT * FROM QLDM";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                QLDMENTITY dm = new QLDMENTITY(
                        rs.getInt("maDM"), 
                        rs.getString("tenDM"));
                Lst.add(dm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Lst;
    }
    
    public void insertDM(QLDMENTITY dm){
        String sql = "INSERT INTO QLDM "
                    + "(tenDM) "
                    + "VALUES (?)";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dm.getTenDM());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
            
    public void updateDM(QLDMENTITY dm){
        String sql = "UPDATE QLDM SET "
                    + "tenDM = ? "
                    + "WHERE maTH = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dm.getTenDM());
            ps.setInt(2, dm.getMaDM());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteDM(int maDM){
        String sql = "DELETE FROM QLDM WHERE maDM = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, maDM);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
