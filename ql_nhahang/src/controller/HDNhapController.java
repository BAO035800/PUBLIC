package controller;

import javax.swing.JOptionPane;
import model.HoaDonNhap;
import model.HoaDonNhapDAO;
import view.khoHoaDonNhap;

public class HDNhapController {
    private khoHoaDonNhap view;
    private HoaDonNhapDAO model;
    private KTKho ktKho;

    public HDNhapController(khoHoaDonNhap view, HoaDonNhapDAO model) {
        this.view = view;
        this.model = model;
        this.ktKho = new KTKho();
    }

    public void themHDNhap() {
        String MaHoaDon = view.getTxtMaHoaDon().getText();
        String MaNguyenLieu = view.getTxtMaNguyenLieu().getText();
        if (!ktKho.kiemTraNguyenLieuTonTai(MaNguyenLieu)) {
            JOptionPane.showMessageDialog(view, "Mã nguyên liệu không tồn tại!");
            return;
        }
        String MaNCC = view.getTxtMaNCC().getText();
        if (!ktKho.kiemTraNCCTonTai(MaNCC)) {
            JOptionPane.showMessageDialog(view, "Mã nhà cung cấp không tồn tại!");
            return;
        }
        if (MaHoaDon.isEmpty() || MaNguyenLieu.isEmpty() || MaNCC.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try {
            HoaDonNhap hoaDonNhap = new HoaDonNhap();
            hoaDonNhap.setMaHoaDonKho(MaHoaDon);
            hoaDonNhap.setMaNguyenLieu(MaNguyenLieu);
            hoaDonNhap.setMaNhaCungCap(MaNCC);
            model.themHoaDonNhap(hoaDonNhap);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm hóa đơn nhập: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void suaHDNhap() {
        String maHoaDon = view.getTxtMaHoaDon().getText();
        String maNguyenLieu = view.getTxtMaNguyenLieu().getText();
        if (!ktKho.kiemTraNguyenLieuTonTai(maNguyenLieu)) {
            JOptionPane.showMessageDialog(view, "Mã nguyên liệu không tồn tại!");
            return;
        }
        String maNCC = view.getTxtMaNCC().getText();
        if (!ktKho.kiemTraNCCTonTai(maNCC)) {
            JOptionPane.showMessageDialog(view, "Mã nhà cung cấp không tồn tại!");
            return;
        }
        if (maHoaDon.isEmpty() || maNguyenLieu.isEmpty() || maNCC.isEmpty()){
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try {
            HoaDonNhap hoaDonNhap = new HoaDonNhap();
            hoaDonNhap.setMaHoaDonKho(maHoaDon);
            hoaDonNhap.setMaNguyenLieu(maNguyenLieu);
            hoaDonNhap.setMaNhaCungCap(maNCC);
            model.suaHoaDonNhap(hoaDonNhap);
            JOptionPane.showMessageDialog(view, "Cập nhật hóa đơn nhập thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật hóa đơn nhập: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void xoaHDNhap(String maHoaDon) {
        // Kiểm tra nếu maHoaDon null hoặc rỗng
        if (maHoaDon == null || maHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn hóa đơn cần xóa!");
            return;
        }
    
        // Hỏi lại trước khi xóa
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa hóa đơn nhập này?", 
                                                     "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
    
        try {
            // Gọi phương thức xóa trong DAO (truyền maHoaDon vào)
            model.xoaHoaDonNhap(maHoaDon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa hóa đơn nhập: " + e.getMessage());
        }
    }
    
    
    
}