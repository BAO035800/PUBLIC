package controller;

import model.HoaDonChiTiet;
import model.HoaDonChiTietDAO;
import model.MenuDAO;
import model.Menu;
import view.banhangChiTiet;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.util.List;

public class HDChiTietController {
    private banhangChiTiet view;
    private HoaDonChiTietDAO model;
    private MenuDAO menuModel;
    private Menu menu;

    public HDChiTietController(banhangChiTiet view, HoaDonChiTietDAO model ,Menu menu) {
        this.view = view;
        this.model = model;
        this.menu=menu;
    }

    public void themHDChiTiet() {
        try {
            int id = Integer.parseInt(view.getTxtID().getText());
            String maMon = view.getTxtMaMon().getText();
            String maHD = view.getTxtMaHoaDonBanHang().getText();
            int soBan = Integer.parseInt(view.getTxtSoBan().getText());
            BigDecimal giaTien = menu.getGiaTien();
            int soLuongDat = Integer.parseInt(view.getTxtSoLuongDat().getText());
            BigDecimal tongTien = new BigDecimal(view.getTxtTongTien().getText());
            
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setID(id);
            hdct.setMaMon(maMon);
            hdct.setMaHoaDonBanHang(maHD);
            hdct.setSoBan(soBan);
            hdct.setGiaTien(giaTien);
            hdct.setSoLuongDat(soLuongDat);
            hdct.setTongTien(tongTien);
            model.themHDChiTiet(hdct);
            JOptionPane.showMessageDialog(view, "Thêm hóa đơn chi tiết thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void suaHDChiTiet() {
        try {
            int id = Integer.parseInt(view.getTxtID().getText());
            String maMon = view.getTxtMaMon().getText();
            String maHD = view.getTxtMaHoaDonBanHang().getText();
            int soBan = Integer.parseInt(view.getTxtSoBan().getText());
            int soLuongDat = Integer.parseInt(view.getTxtSoLuongDat().getText());
            BigDecimal tongTien = new BigDecimal(view.getTxtTongTien().getText());
    
            // Lấy hóa đơn chi tiết từ DB theo ID
            HoaDonChiTiet hdctExist = model.layHoaDonChiTiet(id);

            if (hdctExist == null) {
                JOptionPane.showMessageDialog(view, "Hóa đơn chi tiết không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Giữ nguyên giá tiền như khi thêm
            BigDecimal giaTien = hdctExist.getGiaTien();
    
            // Cập nhật thông tin
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setID(id);
            hdct.setMaMon(maMon);
            hdct.setMaHoaDonBanHang(maHD);
            hdct.setSoBan(soBan);
            hdct.setGiaTien(giaTien);
            hdct.setSoLuongDat(soLuongDat);
            hdct.setTongTien(tongTien);
    
            model.suaHDChiTiet(hdct);
            JOptionPane.showMessageDialog(view, "Sửa hóa đơn chi tiết thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đúng định dạng số!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi sửa hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void xoaHDChiTiet() {
        try {
            int id = Integer.parseInt(view.getTxtID().getText());
    
            // Lấy hóa đơn chi tiết từ DB theo ID
            HoaDonChiTiet hdctExist = model.layHoaDonChiTietById(id);
            if (hdctExist == null) {
                JOptionPane.showMessageDialog(view, "Hóa đơn chi tiết không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Xác nhận trước khi xóa
            int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa hóa đơn chi tiết này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.xoaHDChiTiet(id);
                JOptionPane.showMessageDialog(view, "Xóa hóa đơn chi tiết thành công!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đúng định dạng số!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
