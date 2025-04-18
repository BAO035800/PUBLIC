package controller;
import javax.swing.JOptionPane;
import model.CongThucMonAn;
import model.CongThucMonAnDAO;
import view.bepCongThucMon;

public class CongThucController {
    private bepCongThucMon view;
    private CongThucMonAnDAO dao;

    public CongThucController(bepCongThucMon view, CongThucMonAnDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    public void themCongThuc() {
        String maMon = view.getTxtMaMon().getText().trim();
        String maNguyenLieu = view.getTxtMaNguyenLieu().getText().trim();
        String soLuongStr = view.getTxtSoLuong().getText().trim();

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

        if (dao.exists(maMon, maNguyenLieu)) {
            JOptionPane.showMessageDialog(view, "Công thức này đã tồn tại!");
            return;
        }

        CongThucMonAn ct = new CongThucMonAn();
        ct.setMaMon(maMon);
        ct.setMaNguyenLieu(maNguyenLieu);
        ct.setSoLuong(soLuong);
        dao.themCongThuc(ct);
        JOptionPane.showMessageDialog(view, "Thêm công thức món ăn thành công!");
    }

    public void suaCongThuc() {
        String maMon = view.getTxtMaMon().getText().trim();
        String maNguyenLieu = view.getTxtMaNguyenLieu().getText().trim();
        String soLuongStr = view.getTxtSoLuong().getText().trim();

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

        if (!dao.exists(maMon, maNguyenLieu)) {
            JOptionPane.showMessageDialog(view, "Công thức không tồn tại để sửa!");
            return;
        }

        CongThucMonAn ct = new CongThucMonAn();
        ct.setMaMon(maMon);
        ct.setMaNguyenLieu(maNguyenLieu);
        ct.setSoLuong(soLuong);
        dao.suaCongThuc(ct);
        JOptionPane.showMessageDialog(view, "Cập nhật công thức thành công!");
    }

    public void xoaCongThuc(String maMon, String maNguyenLieu) {
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa công thức này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.xoaCongThuc(maMon, maNguyenLieu);
            JOptionPane.showMessageDialog(view, "Xóa công thức thành công!");
        }
    }
}
