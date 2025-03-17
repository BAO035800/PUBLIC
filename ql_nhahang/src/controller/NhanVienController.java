package controller;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import model.NhanVien;
import model.NhanVienDAO;
import view.nhansuNhanVien;
public class NhanVienController implements KTNhanSu {
    private nhansuNhanVien view;
    private NhanVienDAO model;
    public NhanVienController(nhansuNhanVien view, NhanVienDAO model) {
        this.view = view;
        this.model = model;
        
    }
    public void themNhanVien() {
        String maNV = view.getTxtMaNhanVien().getText();
        String tenNV = view.getTxtTen().getText();
        String chucVu = view.getCbChucVu().getSelectedItem().toString();
        String tuoiStr = view.getTxtTuoi().getText();
        String luongStr = view.getTxtLuong().getText();  
        String soDienThoai = view.getTxtSoDienThoai().getText();
        String gioiTinh = view.getRadNam().isSelected() ? "Nam" : "Nữ";
        if (maNV.isEmpty() || tenNV.isEmpty() || chucVu.isEmpty() || tuoiStr.isEmpty() || luongStr.isEmpty() || soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            BigDecimal luong = new BigDecimal(luongStr);
            int tuoi = Integer.parseInt(tuoiStr);

            // Tạo đối tượng nhân viên
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(maNV);
            nhanVien.setTenNhanVien(tenNV);
            nhanVien.setChucVu(chucVu);
            nhanVien.setTuoi(tuoi);
            nhanVien.setLuong1Gio(luong);
            nhanVien.setGioiTinh(gioiTinh);
            nhanVien.setSoDienThoai(soDienThoai);
            // Gọi model để thêm nhân viên vào database
            model.themNhanVien(nhanVien);
            System.out.println("ChucVu nhận được: " + chucVu);
            JOptionPane.showMessageDialog(view, "Thêm nhân viên thành công!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm nhân viên: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void suaNhanVien() {
        String maNV = view.getTxtMaNhanVien().getText();
        String tenNV = view.getTxtTen().getText();
        String chucVu = view.getCbChucVu().getSelectedItem().toString();
        String tuoiStr = view.getTxtTuoi().getText(); 
        String luongStr = view.getTxtLuong().getText();   
        String soDienThoai = view.getTxtSoDienThoai().getText();
        String gioiTinh = view.getRadNam().isSelected() ? "Nam" : "Nữ";
        if (maNV.isEmpty() || tenNV.isEmpty() || chucVu.isEmpty() || tuoiStr.isEmpty() || luongStr.isEmpty() || soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try {
            BigDecimal luong = new BigDecimal(luongStr);
            int tuoi = Integer.parseInt(tuoiStr);
            // Tạo đối tượng nhân viên
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(maNV);
            nhanVien.setTenNhanVien(tenNV);
            nhanVien.setChucVu(chucVu);
            nhanVien.setTuoi(tuoi);
            nhanVien.setLuong1Gio(luong);
            nhanVien.setGioiTinh(gioiTinh);
            nhanVien.setSoDienThoai(soDienThoai);
            if (!kiemTraNhanVienTonTai(maNV)) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy nhân viên!");
                return;
            }
            // Gọi model để thêm nhân viên vào database
            model.suaNhanVien(nhanVien);
            JOptionPane.showMessageDialog(view, "Sửa nhân viên thành công!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi sửa nhân viên: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public boolean xoaNhanVien(String maNV) {
        if (maNV == null || maNV.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên cần xóa!");
            return false;
        }
        boolean isDeleted = model.xoaNhanVien(maNV);
        if (isDeleted) {
            JOptionPane.showMessageDialog(view, "Xóa nhân viên thành công!");
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Không tìm thấy nhân viên cần xóa!");
            return false;
        }
    }
}
