package controller;
import model.HoaDonBanHangDAO;
import view.banhangHoaDonBanHang;
import model.Connect;
import java.sql.*;
import javax.swing.JOptionPane;

public class HDBanHangController implements KTBanHang {
    private banhangHoaDonBanHang view;
    private HoaDonBanHangDAO model;
    public HDBanHangController(banhangHoaDonBanHang view, HoaDonBanHangDAO model) {
        this.view = view;
        this.model = model;
    }
    public void themHoaDon() {
        String soHoaDon = view.getTxtSoHoaDon().getText();
        if (kiemTraHoaDonTonTai(soHoaDon)) {
            JOptionPane.showMessageDialog(view, "Hóa đơn đã tồn tại!");
            return;
        }
        String ngayBan = view.getTxtNgayBan().getText();
        String maNhanVien = view.getTxtMaNhanVien().getText();
        String tenNhanVien = view.getTxtTenNhanVien().getText();
        String soBan = view.getTxtSoBan().getText();
        String tongTienStr = view.getTxtTongTien().getText();
        if (soHoaDon.isEmpty() || ngayBan.isEmpty() || maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soBan.isEmpty() || tongTienStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try {
            BigDecimal tongTien = new BigDecimal(tongTienStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
            return;
        }
    }
}
