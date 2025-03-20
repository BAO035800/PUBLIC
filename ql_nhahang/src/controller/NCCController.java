package controller;
import javax.swing.JOptionPane;
import model.NhaCungCap;
import model.NhaCungCapDAO;
import view.khoNhaCungCap;
public class NCCController implements KTBanHang {
    private khoNhaCungCap view;
    private NhaCungCapDAO model;
    public NCCController(khoNhaCungCap view, NhaCungCapDAO model) {
        this.view = view;
        this.model = model;
    }
    public void themNhaCungCap() {
        String maNCC = view.getTxtMaNCC().getText();
        String tenNCC = view.getTxtTenNCC().getText();
        String lienHe = view.getTxtLienHe().getText();
        if (maNCC.isEmpty() || tenNCC.isEmpty() || lienHe.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try {
            NhaCungCap nhacungcap = new NhaCungCap();
            nhacungcap.setMaNhaCungCap(maNCC);
            nhacungcap.setTenNhaCungCap(tenNCC);
            nhacungcap.setLienHe(lienHe);
            model.themNhaCungCap(nhacungcap);
            JOptionPane.showMessageDialog(view, "Thêm nhà cung cấp thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm nhà cung cấp: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void suaNhaCungCap() {
        String maNCC = view.getTxtMaNCC().getText();
        String tenNCC = view.getTxtTenNCC().getText();
        String lienHe = view.getTxtLienHe().getText();
        if (maNCC.isEmpty() || tenNCC.isEmpty() || lienHe.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try {
            NhaCungCap nhacungcap = new NhaCungCap();
            nhacungcap.setMaNhaCungCap(maNCC);
            nhacungcap.setTenNhaCungCap(tenNCC);
            nhacungcap.setLienHe(lienHe);
            model.suaNhaCungCap(nhacungcap);
            JOptionPane.showMessageDialog(view, "Cập nhật nhà cung cấp thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật nhà cung cấp: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void xoaNhaCungCap(String maNCC) {
        if (maNCC.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn nhà cung cấp cần xóa!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa nhà cung cấp này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                model.xoaNhaCungCap(maNCC);
                JOptionPane.showMessageDialog(view, "Xóa nhà cung cấp thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Lỗi khi xóa nhà cung cấp: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
