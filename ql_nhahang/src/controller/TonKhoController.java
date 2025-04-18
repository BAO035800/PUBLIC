package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.TonKho;
import model.TonKhoDAO;
import view.khoTonKho;

public class TonKhoController {
    private khoTonKho view;
    private TonKhoDAO dao;

    public TonKhoController(khoTonKho view, TonKhoDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    // Hàm tạo Date từ ComboBox ngày/tháng/năm
    public Date getNgayCapNhatFromCombo() {
    try {
        String ngay = (String) view.getCbNgay().getSelectedItem();
        String thang = (String) view.getCbThang().getSelectedItem();
        String nam = (String) view.getCbNam().getSelectedItem();

        if (ngay == null || thang == null || nam == null) {
            return null;
        }

        String dateString = nam + "-" + thang + "-" + ngay; // yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateString);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


    // Thêm tồn kho
    public void themTonKho(String maNguyenLieu, String soLuong) {
        try {
            // Kiểm tra nếu các trường nhập liệu trống
            if (maNguyenLieu.isEmpty() || soLuong.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
    
            // Kiểm tra nếu soLuong có thể chuyển đổi thành số nguyên
            int sl = 0;
            try {
                sl = Integer.parseInt(soLuong);  // Nếu là số hợp lệ
                if (sl <= 0) {  // Kiểm tra nếu số lượng lớn hơn 0
                    JOptionPane.showMessageDialog(view, "Số lượng phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Số lượng phải là một số hợp lệ!");
                return;
            }
    
            // Tạo đối tượng TonKho để lưu thông tin
            TonKho tonKho = new TonKho();
            tonKho.setMaNguyenLieu(maNguyenLieu);
            tonKho.setSoLuongTon(sl);
            tonKho.setNgayCapNhat(new java.util.Date());  // Đặt ngày cập nhật là hiện tại
    
            // Gọi phương thức thêm tồn kho từ DAO
            dao.themTonKho(tonKho);
    
            // Thông báo thành công
            JOptionPane.showMessageDialog(view, "✅ Đã thêm nguyên liệu vào tồn kho!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "❌ Lỗi khi thêm tồn kho!");
        }
    }
    
    

    // Sửa tồn kho
    public void suaTonKho() {
        try {
            String ma = view.getTxtMaNguyenLieu().getText().trim();
            String soLuongText = view.getTxtSoLuongTon().getText().trim();
            Date ngay = getNgayCapNhatFromCombo();  // Lấy ngày từ ComboBox hoặc DateChooser
    
            // Kiểm tra xem mã nguyên liệu, số lượng và ngày có hợp lệ không
            if (ma.isEmpty() || soLuongText.isEmpty() || ngay == null) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
    
            // Kiểm tra xem số lượng có phải là số nguyên hợp lệ không
            int soLuong;
            try {
                soLuong = Integer.parseInt(soLuongText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Số lượng phải là một số hợp lệ!");
                return;
            }
    
            // Tạo đối tượng TonKho và gọi DAO để sửa thông tin
            TonKho tonKho = new TonKho();
            tonKho.setMaNguyenLieu(ma);
            tonKho.setSoLuongTon(soLuong);
            tonKho.setNgayCapNhat(ngay);
    
            dao.suaTonKho(tonKho);  // Gọi DAO để cập nhật thông tin vào 
            JOptionPane.showMessageDialog(view, "✅ Đã cập nhật tồn kho!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "❌ Lỗi khi cập nhật tồn kho!");
        }
    }
    

    // Xóa tồn kho
    public void xoaTonKho(String ma) {
        try {
            if (ma.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn nguyên liệu để xóa!");
                return;
            }
    
            int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.xoaTonKho(ma);  // Gọi phương thức xóa trong DAO với tham số ma
                JOptionPane.showMessageDialog(view, "✅ Đã xóa nguyên liệu khỏi tồn kho!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "❌ Lỗi khi xóa tồn kho!");
        }
    }
    
}
