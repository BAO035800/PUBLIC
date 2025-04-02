package controller;

import javax.swing.JOptionPane;
import model.HoaDonBanHang;
import model.HoaDonBanHangDAO;
import view.banhangHoaDonBanHang;
public class HDBanHangController {
    private banhangHoaDonBanHang view;
    private HoaDonBanHangDAO model;

    public HDBanHangController(banhangHoaDonBanHang view, HoaDonBanHangDAO model) {
        this.view = view;
        this.model = model;
    }

    public void themHDBanHang() {
        String maHD = view.getTxtMaHD().getText();
        String soBan = view.getTxtSoBan().getText();
        String ghiChu = view.getTxtGhiChu().getText();
    
        if (maHD.isEmpty() || soBan.isEmpty()) {
            JOptionPane.showMessageDialog(view, "⚠️ Vui lòng nhập đầy đủ thông tin!");
            return;
        }
    
        try {
            int soBanInt = Integer.parseInt(soBan); // Chuyển String -> int
            
            KTBanHang controller = new KTBanHang();
        
            // Kiểm tra xem bàn có tồn tại không
            if (!controller.kiemTraBanTonTai(soBan)) {  // Truyền soBan dạng String vào
                JOptionPane.showMessageDialog(view, "❌ Không tìm thấy bàn số " + soBan + " trong hệ thống!");
                return;
            }
        
            HoaDonBanHang hoaDon = new HoaDonBanHang();
            hoaDon.setMaHoaDonBanHang(maHD);
            hoaDon.setSoBan(soBanInt); 
            hoaDon.setGhiChu(ghiChu);
            
            model.themHoaDon(hoaDon);
            JOptionPane.showMessageDialog(view, "✅ Thêm hóa đơn bán hàng thành công!");
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "⚠️ Định dạng số bàn không hợp lệ!");
        }
        
    }
    public void suaHDBanHang() {
        String maHD = view.getTxtMaHD().getText();
        String soBan = view.getTxtSoBan().getText();
        String ghiChu = view.getTxtGhiChu().getText();

        if (maHD.isEmpty() || soBan.isEmpty() ) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        
        try {
            int soBanInt = Integer.parseInt(soBan);
            HoaDonBanHang hoaDon = new HoaDonBanHang();
            hoaDon.setMaHoaDonBanHang(maHD);
            hoaDon.setSoBan(soBanInt);
            hoaDon.setGhiChu(ghiChu);
            model.suaHoaDon(hoaDon);
            JOptionPane.showMessageDialog(view, "Cập nhật hóa đơn thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng số không hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void xoaHDBanHang(String maHD) {
        if (maHD == null || maHD.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn hóa đơn cần xóa!");
            return;
        }
        try {
            model.xoaHoaDon(maHD);
            JOptionPane.showMessageDialog(view, "Xóa hóa đơn thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
}
