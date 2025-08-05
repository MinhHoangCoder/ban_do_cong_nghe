/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.*;
import java.sql.*;
import entity.QLSPENTITY;
import utils.ConnectDB;

/**
 *
 * @author MSI USER
 */
public class QLSPDAO {
    public void updateQuantity(int maSP){
        String sql = "update QLSP set soLuongSP -= 1 where maSP = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    public Map<String, Integer> getTH(){
        Map<String, Integer> thmap = new HashMap();
        String sql = "select * from ThuongHieu";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                String tenTH = rs.getString("tenTH");
                int maTH = rs.getInt("maTH");
                thmap.put(tenTH, maTH);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thmap;
    }
    
    public Map<String, Integer> getDM(){
        Map<String, Integer> dmmap = new HashMap();
        String sql = "select * from QLDM";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                String tenDM = rs.getString("tenDM");
                int maDM = rs.getInt("maDM");
                dmmap.put(tenDM, maDM);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dmmap;
    }
    
    public List<QLSPENTITY> getAllSP(){
        List<QLSPENTITY> Lst = new ArrayList<>();
        String sql = "select sp.*, dm.tenDM, th.tenTH from QLSP sp "
                + "join ThuongHieu th on th.maTH = sp.maTH "
                + "join QLDM dm on dm.maDM = sp.maDM";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                QLSPENTITY sp = new QLSPENTITY(
                    rs.getInt("maSP"), 
                    rs.getInt("maDM"), 
                    rs.getInt("maTH"), 
                    rs.getInt("soLuongSP"), 
                    rs.getInt("trangThaiSP"), 
                    rs.getFloat("gia"),
                    rs.getString("tenSP"),
                    rs.getString("moTa"));
                Lst.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Lst;
    }
    
    public void insertSP(QLSPENTITY sp){
        String sql = "insert into QLSP "
                + "(maDM, maTH, tenSP, gia, soLuongSP, trangThaiSP, moTa) values "
                + "(?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sp.getMaDM());
            ps.setInt(2, sp.getMaTH());
            ps.setString(3, sp.getTenSP());
            ps.setFloat(4, sp.getGiaSP());
            ps.setInt(5, sp.getSoLuongSP());
            ps.setInt(6, sp.getTrangThaiSP());
            ps.setString(7, sp.getMoTa());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateSP(QLSPENTITY sp){
        String sql = "update QLSP set "
                + "maDM = ?, "
                + "maTH = ?, "
                + "tenSP = ?, "
                + "gia = ?, "
                + "soLuongSP = ?, "
                + "trangThaiSP = ?, "
                + "moTa = ? "
                + "where maSP = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sp.getMaDM());
            ps.setInt(2, sp.getMaTH());
            ps.setString(3, sp.getTenSP());
            ps.setFloat(4, sp.getGiaSP());
            ps.setInt(5, sp.getSoLuongSP());
            ps.setInt(6, sp.getTrangThaiSP());
            ps.setString(7, sp.getMoTa());
            ps.setInt(8, sp.getMaSP());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteSP(int maSP){
        String sql = "delete from QLSP where maSP = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public QLSPENTITY getOneSP(int id){
        String sql = "select sp.*, dm.tenDM, th.tenTH from QLSP sp "
                + "join ThuongHieu th on th.maTH = sp.maTH "
                + "join QLDM dm on dm.maDM = sp.maDM where sp.maSP = ?";
        try(Connection con = ConnectDB.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                QLSPENTITY sp = new QLSPENTITY(
                    rs.getInt("maSP"), 
                    rs.getInt("maDM"), 
                    rs.getInt("maTH"), 
                    rs.getInt("soLuongSP"), 
                    rs.getInt("trangThaiSP"), 
                    rs.getFloat("gia"),
                    rs.getString("tenSP"),
                    rs.getString("moTa"));
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
