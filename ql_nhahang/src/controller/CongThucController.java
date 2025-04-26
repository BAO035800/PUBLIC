package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.CongThucMonAn;
import model.CongThucMonAnDAO;
import model.connectData;
import view.bepCongThucMon;

public class CongThucController {
    private bepCongThucMon view;
    private CongThucMonAnDAO dao;

    public CongThucController(bepCongThucMon view, CongThucMonAnDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    // Kiểm tra mã món có tồn tại trong bảng menu
    private boolean kiemTraMaMonTonTai(String maMon) throws SQLException {
        String sql = "SELECT COUNT(*) FROM menu WHERE MaMon = ?";
        try (Connection conn = connectData.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maMon);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Kiểm tra mã nguyên liệu có tồn tại trong bảng khonguyenlieu
    private boolean kiemTraMaNguyenLieuTonTai(String maNguyenLieu) throws SQLException {
        String sql = "SELECT COUNT(*) FROM khonguyenlieu WHERE MaNguyenLieu = ?";
        try (Connection conn = connectData.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maNguyenLieu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public void themCongThuc() {
        try {
            // Lấy dữ liệu từ view
            String maMon = view.getTxtMaMon().getText().trim();
            String maNguyenLieu = view.getTxtMaNguyenLieu().getText().trim();
            String soLuongStr = view.getTxtSoLuong().getText().trim();

            // Kiểm tra dữ liệu bắt buộc
            if (maMon.isEmpty() || maNguyenLieu.isEmpty() || soLuongStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            float soLuong;
            try {
                soLuong = (float) Double.parseDouble(soLuongStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Số lượng phải là số!");
                return;
            }

            // Kiểm tra mã món có tồn tại trong bảng menu
            if (!kiemTraMaMonTonTai(maMon)) {
                JOptionPane.showMessageDialog(view, "Mã món không tồn tại trong bảng menu!");
                return;
            }

            // Kiểm tra mã nguyên liệu có tồn tại trong bảng khonguyenlieu
            if (!kiemTraMaNguyenLieuTonTai(maNguyenLieu)) {
                JOptionPane.showMessageDialog(view, "Mã nguyên liệu không tồn tại trong bảng khonguyenlieu!");
                return;
            }

            // Kiểm tra công thức đã tồn tại chưa
            if (dao.exists(maMon, maNguyenLieu)) {
                JOptionPane.showMessageDialog(view, "Công thức này đã tồn tại!");
                return;
            }

            // Tạo đối tượng model CongThucMonAn
            CongThucMonAn ct = new CongThucMonAn();
            ct.setMaMon(maMon);
            ct.setMaNguyenLieu(maNguyenLieu);
            ct.setSoLuong(soLuong);

            // Gọi phương thức thêm trong DAO
            dao.themCongThuc(ct);
            JOptionPane.showMessageDialog(view, "Thêm công thức món ăn thành công!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi kiểm tra dữ liệu: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm công thức món: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void suaCongThuc() {
        try {
            // Lấy dữ liệu từ view
            String maMon = view.getTxtMaMon().getText().trim();
            String maNguyenLieu = view.getTxtMaNguyenLieu().getText().trim();
            String soLuongStr = view.getTxtSoLuong().getText().trim();

            // Kiểm tra dữ liệu bắt buộc
            if (maMon.isEmpty() || maNguyenLieu.isEmpty() || soLuongStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            float soLuong;
            try {
                soLuong = Float.parseFloat(soLuongStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Số lượng phải là số!");
                return;
            }

            // Kiểm tra mã món có tồn tại trong bảng menu
            if (!kiemTraMaMonTonTai(maMon)) {
                JOptionPane.showMessageDialog(view, "Mã món không tồn tại trong bảng menu!");
                return;
            }

            // Kiểm tra mã nguyên liệu có tồn tại trong bảng khonguyenlieu
            if (!kiemTraMaNguyenLieuTonTai(maNguyenLieu)) {
                JOptionPane.showMessageDialog(view, "Mã nguyên liệu không tồn tại trong bảng khonguyenlieu!");
                return;
            }

            // Kiểm tra công thức có tồn tại để sửa không
            if (!dao.exists(maMon, maNguyenLieu)) {
                JOptionPane.showMessageDialog(view, "Công thức không tồn tại để sửa!");
                return;
            }

            // Tạo đối tượng model CongThucMonAn
            CongThucMonAn ct = new CongThucMonAn();
            ct.setMaMon(maMon);
            ct.setMaNguyenLieu(maNguyenLieu);
            ct.setSoLuong(soLuong);

            // Gọi phương thức cập nhật trong DAO
            dao.suaCongThuc(ct);
            JOptionPane.showMessageDialog(view, "Cập nhật công thức thành công!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi kiểm tra dữ liệu: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật công thức: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void xoaCongThuc(String maMon, String maNguyenLieu) {
        try {
            if (maMon == null || maMon.trim().isEmpty() || maNguyenLieu == null || maNguyenLieu.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn công thức cần xóa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view, 
                    "Bạn có chắc chắn muốn xóa công thức này?", 
                    "Xác nhận", 
                    JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            dao.xoaCongThuc(maMon, maNguyenLieu);
            JOptionPane.showMessageDialog(view, "Xóa công thức thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa công thức: " + e.getMessage());
            e.printStackTrace();
        }
    }
}