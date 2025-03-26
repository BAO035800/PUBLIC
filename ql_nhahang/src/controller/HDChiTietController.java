package controller;

import model.HoaDonChiTiet;
import model.HoaDonChiTietDAO;
import model.MenuDAO;
import view.banhangChiTiet;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.util.List;

public class HDChiTietController {
    private banhangChiTiet view;
    private HoaDonChiTietDAO model;
    private MenuDAO menuModel;

    public HDChiTietController(banhangChiTiet view, HoaDonChiTietDAO model ,MenuDAO menuModel) {
        this.view = view;
        this.model = model;
        this.menuModel=menuModel;
    }

    public void themHDChiTiet() {
        try {
            int id = Integer.parseInt(view.getTxtID().getText());
            String maMon = view.getTxtMaMon().getText();
            String maHD = view.getTxtMaHoaDonBanHang().getText();
            int soBan = Integer.parseInt(view.getTxtSoBan().getText());
            BigDecimal giaTien = menuModel.getGiaTien(giaTien);
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
            BigDecimal giaTien = new BigDecimal(view.getTxtGiaTien().getText());
            int soLuongDat = Integer.parseInt(view.getTxtSoLuongDat().getText());
            BigDecimal tongTien = new BigDecimal(view.getTxtTongTien().getText());
            
            List<HoaDonChiTiet> danhSach = model.layDanhSachHoaDonChiTiet();
            boolean tonTai = danhSach.stream().anyMatch(h -> h.getID() == id);
            
            if (!tonTai) {
                JOptionPane.showMessageDialog(view, "Hóa đơn chi tiết không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi sửa hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void xoaHDChiTiet() {
        try {
            int id = Integer.parseInt(view.getTxtID().getText());
            
            List<HoaDonChiTiet> danhSach = model.layDanhSachHoaDonChiTiet();
            boolean tonTai = danhSach.stream().anyMatch(h -> h.getID() == id);
            
            if (!tonTai) {
                JOptionPane.showMessageDialog(view, "Hóa đơn chi tiết không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            model.xoaHDChiTiet(String.valueOf(id));
            JOptionPane.showMessageDialog(view, "Xóa hóa đơn chi tiết thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
