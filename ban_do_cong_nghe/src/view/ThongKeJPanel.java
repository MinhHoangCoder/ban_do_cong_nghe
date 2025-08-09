/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.ThongKeDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author MSI USER
 */
public class ThongKeJPanel extends javax.swing.JPanel {

    private DefaultTableModel thongKeModel;
    private ThongKeDAO thongKeDao;
    private JSpinner spinnerStartDate;
    private JSpinner spinnerEndDate;
    private JPanel pnlCustomDate;
    /**
     * Creates new form ThongKeJPanel
     */
    public ThongKeJPanel() {
        initComponents();
        setupComponents();
    }
    
     private void setupComponents() {
        // Khởi tạo DAO
        thongKeDao = new ThongKeDAO();
        
        // Setup table model
        thongKeModel = (DefaultTableModel) tblThongKe.getModel();
        
        // Tạo panel tùy chỉnh ngày (ẩn mặc định)
        createCustomDatePanel();
        
        // Setup ComboBox
        setupComboBox();
        
        // Load dữ liệu mặc định
        cbThoiGian.setSelectedItem("Tùy chỉnh");
    }
    
    private void setupComboBox() {
        cbThoiGian.removeAllItems();
        cbThoiGian.addItem("Tùy chỉnh");
        cbThoiGian.addItem("Hôm nay");
        cbThoiGian.addItem("Hôm qua");
        cbThoiGian.addItem("7 ngày qua");
        cbThoiGian.addItem("Tháng này");
        cbThoiGian.addItem("Tháng trước");
        cbThoiGian.addItem("3 tháng qua");
        cbThoiGian.addItem("Năm này");
        cbThoiGian.addItem("Năm trước");
        
        // Set mặc định là "Tùy chỉnh"
        cbThoiGian.setSelectedIndex(0);
    }
    
    private void createCustomDatePanel() {
        pnlCustomDate = new JPanel();
        pnlCustomDate.setLayout(new java.awt.FlowLayout());
        
        // Tạo spinners
        SpinnerDateModel startModel = new SpinnerDateModel();
        SpinnerDateModel endModel = new SpinnerDateModel();
        
        spinnerStartDate = new JSpinner(startModel);
        spinnerEndDate = new JSpinner(endModel);
        
        // Format hiển thị
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(spinnerStartDate, "dd/MM/yyyy");
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(spinnerEndDate, "dd/MM/yyyy");
        
        spinnerStartDate.setEditor(startEditor);
        spinnerEndDate.setEditor(endEditor);
        
        // Thêm vào panel
        pnlCustomDate.add(new JLabel("Từ ngày:"));
        pnlCustomDate.add(spinnerStartDate);
        pnlCustomDate.add(new JLabel("Đến ngày:"));
        pnlCustomDate.add(spinnerEndDate);
        
        // Thêm vào header panel (giả sử pnlHeader đã tồn tại)
        pnlHeader.add(pnlCustomDate);
        
        // Ẩn mặc định
        pnlCustomDate.setVisible(true); // Hiện vì mặc định là "Tùy chỉnh"
    }
    
    private LocalDate[] calculateDateRange(String option) {
        LocalDate today = LocalDate.now();
        LocalDate startDate, endDate;
        
        switch (option) {
            case "Hôm nay":
                startDate = today;
                endDate = today;
                break;
            case "Hôm qua":
                startDate = today.minusDays(1);
                endDate = today.minusDays(1);
                break;
            case "7 ngày qua":
                startDate = today.minusDays(6);
                endDate = today;
                break;
            case "Tháng này":
                startDate = today.withDayOfMonth(1);
                endDate = today;
                break;
            case "Tháng trước":
                startDate = today.minusMonths(1).withDayOfMonth(1);
                endDate = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth());
                break;
            case "3 tháng qua":
                startDate = today.minusMonths(2).withDayOfMonth(1);
                endDate = today;
                break;
            case "Năm này":
                startDate = today.withDayOfYear(1);
                endDate = today;
                break;
            case "Năm trước":
                startDate = today.minusYears(1).withDayOfYear(1);
                endDate = today.minusYears(1).withDayOfYear(today.minusYears(1).lengthOfYear());
                break;
            default:
                startDate = today;
                endDate = today;
        }
        
        return new LocalDate[]{startDate, endDate};
    }
    
    private void loadThongKe(LocalDate startDate, LocalDate endDate) {
        try {
            // Clear table
            thongKeModel.setRowCount(0);
            
            // Lấy dữ liệu từ DAO
            Map<String, Object> thongKe = thongKeDao.getThongKe(startDate, endDate);
            
            // Hiển thị lên table
            thongKeModel.addRow(new Object[]{
                "Tổng doanh thu", 
                formatCurrency((Float) thongKe.get("tongDoanhThu")), 
                "VNĐ"
            });
            
            thongKeModel.addRow(new Object[]{
                "Số HĐ đã thanh toán", 
                thongKe.get("soHoaDon"), 
                "Hóa đơn"
            });
            
            thongKeModel.addRow(new Object[]{
                "SL sản phẩm bán ra", 
                thongKe.get("soLuongSP"), 
                "Sản phẩm"
            });
            
            thongKeModel.addRow(new Object[]{
                "Doanh thu TB/HĐ", 
                formatCurrency((Float) thongKe.get("doanhThuTB")), 
                "VNĐ"
            });
            
            thongKeModel.addRow(new Object[]{
                "SP bán chạy nhất", 
                thongKe.get("spBanChay"), 
                ""
            });
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Có lỗi khi tải dữ liệu thống kê: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String formatCurrency(float amount) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
        return nf.format(amount);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlHeader = new javax.swing.JPanel();
        cbThoiGian = new javax.swing.JComboBox<>();
        btnCapNhat = new javax.swing.JButton();
        JScrollPane = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("Thống Kê");

        cbThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbThoiGian.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbThoiGianItemStateChanged(evt);
            }
        });
        pnlHeader.add(cbThoiGian);

        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/Gear.png"))); // NOI18N
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlHeader.add(btnCapNhat);

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Chỉ số thống kê", "Giá trị", "Đơn vị"
            }
        ));
        JScrollPane.setViewportView(tblThongKe);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(472, 472, 472)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 467, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JScrollPane)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbThoiGianItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbThoiGianItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selected = (String) cbThoiGian.getSelectedItem();
            
            if ("Tùy chỉnh".equals(selected)) {
                pnlCustomDate.setVisible(true);
            } else {
                pnlCustomDate.setVisible(false);
                // Tự động cập nhật khi chọn option khác
                btnCapNhatActionPerformed(null);
            }
            
            // Refresh layout
            pnlHeader.revalidate();
            pnlHeader.repaint();
        }
    }//GEN-LAST:event_cbThoiGianItemStateChanged

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        String selectedOption = (String) cbThoiGian.getSelectedItem();
        LocalDate startDate, endDate;
        
        if ("Tùy chỉnh".equals(selectedOption)) {
            // Kiểm tra người dùng đã chọn ngày chưa
            Date startDateObj = (Date) spinnerStartDate.getValue();
            Date endDateObj = (Date) spinnerEndDate.getValue();
            
            if (startDateObj.after(endDateObj)) {
                JOptionPane.showMessageDialog(this, 
                    "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            startDate = startDateObj.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            endDate = endDateObj.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            
        } else {
            // Tính toán ngày theo lựa chọn
            LocalDate[] dateRange = calculateDateRange(selectedOption);
            startDate = dateRange[0];
            endDate = dateRange[1];
        }
        
        // Load và hiển thị thống kê
        loadThongKe(startDate, endDate);
    }//GEN-LAST:event_btnCapNhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JComboBox<String> cbThoiGian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTable tblThongKe;
    // End of variables declaration//GEN-END:variables
}
