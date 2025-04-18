package controller;

import javax.swing.JOptionPane;
import model.PhanCongBep;
import model.PhanCongBepDAO;
import view.bepPhanCongBep;

public class PhanCongBepController {
    private bepPhanCongBep view;
    private PhanCongBepDAO dao;

    public PhanCongBepController(bepPhanCongBep view, PhanCongBepDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    public void themPhanCong() {
        String maCheBien = view.getTxtMaCheBien().getText().trim();
        String maNhanVien = view.getTxtMaNhanVien().getText().trim();
        String vaiTro = view.getTxtVaiTro().getText().trim();

        if (maCheBien.isEmpty() || maNhanVien.isEmpty() || vaiTro.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        // Kiểm tra tồn tại trước khi thêm
        if (dao.exists(maCheBien, maNhanVien)) {
            JOptionPane.showMessageDialog(view, "Phân công này đã tồn tại!");
            return;
        }

        PhanCongBep pc = new PhanCongBep();
        pc.setMaCheBien(maCheBien);
        pc.setMaNhanVien(maNhanVien);
        pc.setVaiTro(vaiTro);
        dao.themPhanCong(pc);
        JOptionPane.showMessageDialog(view, "Thêm phân công bếp thành công!");
    }

    public void suaPhanCong() {
        String maCheBien = view.getTxtMaCheBien().getText().trim();
        String maNhanVien = view.getTxtMaNhanVien().getText().trim();
        String vaiTro = view.getTxtVaiTro().getText().trim();

        if (maCheBien.isEmpty() || maNhanVien.isEmpty() || vaiTro.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        if (!dao.exists(maCheBien, maNhanVien)) {
            JOptionPane.showMessageDialog(view, "Phân công này không tồn tại để sửa!");
            return;
        }

        PhanCongBep pc = new PhanCongBep();
        pc.setMaCheBien(maCheBien);
        pc.setMaNhanVien(maNhanVien);
        pc.setVaiTro(vaiTro);
        dao.suaPhanCong(pc);
        JOptionPane.showMessageDialog(view, "Cập nhật phân công bếp thành công!");
    }

    public void xoaPhanCong(String maCheBien, String maNhanVien) {
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa phân công này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.xoaPhanCong(maCheBien, maNhanVien);
            JOptionPane.showMessageDialog(view, "Xóa phân công bếp thành công!");
        }
    }
}
