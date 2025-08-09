package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import utils.ConnectDB;

/**
 *
 * @author MSI USER
 */
public class ThongKeDAO {

    private Connection connection;
    
    public ThongKeDAO() {
        this.connection = ConnectDB.getConnect();
    }
    
    // Lấy thống kê theo khoảng thời gian
    public Map<String, Object> getThongKe(LocalDate startDate, LocalDate endDate) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // Tổng doanh thu
            float tongDoanhThu = getTongDoanhThu(startDate, endDate);
            result.put("tongDoanhThu", tongDoanhThu);
            
            // Số hóa đơn đã thanh toán
            int soHoaDon = getSoHoaDonThanhToan(startDate, endDate);
            result.put("soHoaDon", soHoaDon);
            
            // Số lượng sản phẩm bán ra
            int soLuongSP = getSoLuongSanPhamBan(startDate, endDate);
            result.put("soLuongSP", soLuongSP);
            
            // Doanh thu trung bình
            float doanhThuTB = soHoaDon > 0 ? tongDoanhThu / soHoaDon : 0;
            result.put("doanhThuTB", doanhThuTB);
            
            // Sản phẩm bán chạy nhất
            String spBanChay = getSanPhamBanChayNhat(startDate, endDate);
            result.put("spBanChay", spBanChay);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    private float getTongDoanhThu(LocalDate startDate, LocalDate endDate) throws SQLException {
        String sql = """
            SELECT ISNULL(SUM(tongTien), 0) as tongDoanhThu
            FROM QLHD 
            WHERE trangThai = 1 
            AND CAST(ngayLap as DATE) BETWEEN ? AND ?
        """;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getFloat("tongDoanhThu");
            }
        }
        return 0;
    }
    
    private int getSoHoaDonThanhToan(LocalDate startDate, LocalDate endDate) throws SQLException {
        String sql = """
            SELECT COUNT(*) as soHoaDon
            FROM QLHD 
            WHERE trangThai = 1 
            AND CAST(ngayLap as DATE) BETWEEN ? AND ?
        """;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("soHoaDon");
            }
        }
        return 0;
    }
    
    private int getSoLuongSanPhamBan(LocalDate startDate, LocalDate endDate) throws SQLException {
        String sql = """
            SELECT ISNULL(SUM(ct.soLuong), 0) as soLuongSP
            FROM QLCTHD ct
            JOIN QLHD hd ON ct.maHD = hd.maHD
            WHERE hd.trangThai = 1 
            AND CAST(hd.ngayLap as DATE) BETWEEN ? AND ?
        """;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("soLuongSP");
            }
        }
        return 0;
    }
    
    private String getSanPhamBanChayNhat(LocalDate startDate, LocalDate endDate) throws SQLException {
        String sql = """
            SELECT TOP 1 ct.tenSP, SUM(ct.soLuong) as tongSoLuong
            FROM QLCTHD ct
            JOIN QLHD hd ON ct.maHD = hd.maHD
            WHERE hd.trangThai = 1 
            AND CAST(hd.ngayLap as DATE) BETWEEN ? AND ?
            GROUP BY ct.tenSP
            ORDER BY tongSoLuong DESC
        """;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("tenSP");
            }
        }
        return "Không có";
    }
}
