package controller;

import javax.swing.JOptionPane;
import model.KhoNguyenLieu;
import model.KhoNguyenLieuDAO;
import view.khoNguyenLieu;

public class NguyenLieuController implements KTKho {
    private khoNguyenLieu view;
    private KhoNguyenLieuDAO model;

    public NguyenLieuController (khoNguyenLieu view, KhoNguyenLieuDAO model) {
        this.view = view;
        this.model = model;
    }
    public void themNguyenLieu() {
        String maNguyenLieu = view.getTxtMaNguyenLieu().getText();
        String tenNguyenLieu = view.getTxtTenNguyenLieu().getText();
        String maNCC = view.getTxtMaNCC().getText();
        if (!kiemTraNCCTonTai(maNCC)) {
            JOptionPane.showMessageDialog(view, "Mã nhà cung cấp không tồn tại!");
            return;
        }
        String soLuongStr = view.getTxtSoLuong().getText();
        if (maNguyenLieu.isEmpty() || tenNguyenLieu.isEmpty() || maNCC.isEmpty() || soLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try {
            int soLuong = Integer.parseInt(soLuongStr);
            KhoNguyenLieu khoNguyenLieu = new KhoNguyenLieu();
            khoNguyenLieu.setMaNguyenLieu(maNguyenLieu);
            khoNguyenLieu.setTenNguyenLieu(tenNguyenLieu);
            khoNguyenLieu.setMaNhaCungCap(maNCC);
            khoNguyenLieu.setSoLuong(soLuong);
            model.themNguyenLieu(khoNguyenLieu);
            JOptionPane.showMessageDialog(view, "Thêm nguyên liệu thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm nguyên liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public void suaNguyenLieu() {
        String maNguyenLieu = view.getTxtMaNguyenLieu().getText();
        String tenNguyenLieu = view.getTxtTenNguyenLieu().getText();
        String maNCC = view.getTxtMaNCC().getText();
        String soLuongStr = view.getTxtSoLuong().getText();
    
        if (maNguyenLieu.isEmpty() || tenNguyenLieu.isEmpty() || maNCC.isEmpty() || soLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        
        if (!kiemTraNCCTonTai(maNCC)) {
            JOptionPane.showMessageDialog(view, "Mã nhà cung cấp không tồn tại!");
            return;
        }
    
        try {
            int soLuong = Integer.parseInt(soLuongStr);
            KhoNguyenLieu khoNguyenLieu = new KhoNguyenLieu();
            khoNguyenLieu.setMaNguyenLieu(maNguyenLieu);
            khoNguyenLieu.setTenNguyenLieu(tenNguyenLieu);
            khoNguyenLieu.setMaNhaCungCap(maNCC);
            khoNguyenLieu.setSoLuong(soLuong);
            model.suaNguyenLieu(khoNguyenLieu);
            JOptionPane.showMessageDialog(view, "Cập nhật nguyên liệu thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng số không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật nguyên liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void xoaNguyenLieu(String maNguyenLieu) {
        if (maNguyenLieu.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập mã nguyên liệu cần xóa!");
            return;
        }
    
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa nguyên liệu này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                model.xoaNguyenLieu(maNguyenLieu);
                JOptionPane.showMessageDialog(view, "Xóa nguyên liệu thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Lỗi khi xóa nguyên liệu: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
    