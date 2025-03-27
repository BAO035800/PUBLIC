package controller;

import java.math.BigDecimal;
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
        String tongTienStr = view.getTxtTongTien().getText();
        String ghiChu = view.getTxtGhiChu().getText();

        if (maHD.isEmpty() || soBan.isEmpty() || tongTienStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        
        try {
            int soBanInt = Integer.parseInt(soBan);
            BigDecimal tongTien = new BigDecimal(tongTienStr);
            HoaDonBanHang hoaDon = new HoaDonBanHang();
            hoaDon.setMaHoaDonBanHang(maHD);
            hoaDon.setSoBan(soBanInt);
            hoaDon.setTongTienHoaDon(tongTien);
            hoaDon.setGhiChu(ghiChu);
            model.themHoaDon(hoaDon);
            JOptionPane.showMessageDialog(view, "Thêm hóa đơn bán hàng thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng số không hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void suaHDBanHang() {
        String maHD = view.getTxtMaHD().getText();
        String soBan = view.getTxtSoBan().getText();
        String tongTienStr = view.getTxtTongTien().getText();
        String ghiChu = view.getTxtGhiChu().getText();

        if (maHD.isEmpty() || soBan.isEmpty() || tongTienStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        
        try {
            int soBanInt = Integer.parseInt(soBan);
            BigDecimal tongTien = new BigDecimal(tongTienStr);
            
            HoaDonBanHang hoaDon = new HoaDonBanHang();
            hoaDon.setMaHoaDonBanHang(maHD);
            hoaDon.setSoBan(soBanInt);
            hoaDon.setTongTienHoaDon(tongTien);
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

    public void xoaHDBanHang() {
        String maHD = view.getTxtMaHD().getText();
    
        if (maHD.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn hóa đơn cần xóa!");
            return;
        }
    
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa hóa đơn này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
    
        try {
            model.xoaHoaDon(maHD); 
            JOptionPane.showMessageDialog(view, "Xóa hóa đơn thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa hóa đơn: " + e.getMessage());
        }
    }
    
}
