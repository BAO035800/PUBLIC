package controller;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import model.HoaDonNhap;
import model.HoaDonNhapDAO;
import view.khoHoaDonNhap;
public class HDNhapController implements KTKho {
    private khoHoaDonNhap view;
    private HoaDonNhapDAO model;
    public HDNhapController(khoHoaDonNhap view,HoaDonNhapDAO model){
        this.view=view;
        this.model=model;
    }
    public void themHDNhap(){
        String MaHoaDon=view.getTxtMaHoaDon().getText();
        String MaNguyenLieu=view.getTxtMaNguyenLieu().getText();
        if(!kiemTraNguyenLieuTonTai(MaNguyenLieu)){
            JOptionPane.showMessageDialog(view, "Mã nguyên liệu không tồn tại!");
            return;
        }
        String MaNCC=view.getTxtMaNCC().getText();
        if(!kiemTraNCCTonTai(MaNCC)){
            JOptionPane.showMessageDialog(view, "Mã nhà cung cấp không tồn tại!");
            return;
        }
        String SoLuong =view.getTxtSoLuong().getText().toString();
        String GiaNhap=view.getTxtGiaNhap().getText().toString();
        if (MaHoaDon.isEmpty()||MaNguyenLieu.isEmpty()||MaNCC.isEmpty()||SoLuong.isEmpty()||GiaNhap.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try{
            int soLuong = Integer.parseInt(SoLuong);
            BigDecimal tongTienNhap = new BigDecimal(GiaNhap);
            HoaDonNhap HoaDonNhap=new HoaDonNhap();
            HoaDonNhap.setMaHoaDonKho(MaHoaDon);
            HoaDonNhap.setMaNguyenLieu(MaNguyenLieu);
            HoaDonNhap.setMaNhaCungCap(MaNCC);
            HoaDonNhap.setSoLuong(soLuong);
            HoaDonNhap.setTongTienNhap(tongTienNhap);
            model.themHoaDonNhap(HoaDonNhap);
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm tiền lương: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void suaHDNhap() {
        String maHoaDon = view.getTxtMaHoaDon().getText();
    
        String maNguyenLieu = view.getTxtMaNguyenLieu().getText();
        if (!kiemTraNguyenLieuTonTai(maNguyenLieu)) {
            JOptionPane.showMessageDialog(view, "Mã nguyên liệu không tồn tại!");
            return;
        }
    
        String maNCC = view.getTxtMaNCC().getText();
        if (!kiemTraNCCTonTai(maNCC)) {
            JOptionPane.showMessageDialog(view, "Mã nhà cung cấp không tồn tại!");
            return;
        }
    
        String soLuongStr = view.getTxtSoLuong().getText();
        String giaNhapStr = view.getTxtGiaNhap().getText();
    
        // Kiểm tra nhập đủ thông tin
        if (maHoaDon.isEmpty() || maNguyenLieu.isEmpty() || maNCC.isEmpty() || soLuongStr.isEmpty() || giaNhapStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
    
        try {
            // Chuyển đổi dữ liệu
            int soLuong = Integer.parseInt(soLuongStr);
            BigDecimal giaNhap = new BigDecimal(giaNhapStr);
    
            // Tạo đối tượng hóa đơn nhập
            HoaDonNhap hoaDonNhap = new HoaDonNhap();
            hoaDonNhap.setMaHoaDonKho(maHoaDon);
            hoaDonNhap.setMaNguyenLieu(maNguyenLieu);
            hoaDonNhap.setMaNhaCungCap(maNCC);
            hoaDonNhap.setSoLuong(soLuong);
            hoaDonNhap.setTongTienNhap(giaNhap);
    
            // Gọi phương thức sửa trong DAO
            model.suaHoaDonNhap(hoaDonNhap);
            JOptionPane.showMessageDialog(view, "Cập nhật hóa đơn nhập thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật hóa đơn nhập: " + e.getMessage());
            e.printStackTrace();
        }
    }
        public boolean xoaHDNhap() {
            String maHoaDon = view.getTxtMaHoaDon().getText();
        
            // Kiểm tra nếu chưa chọn hóa đơn
            if (maHoaDon == null || maHoaDon.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn hóa đơn cần xóa!");
                return false;
            }
        
            // Hỏi lại trước khi xóa
            int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa hóa đơn nhập này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return false;
            }
        
            // Gọi phương thức xóa trong DAO
            boolean isDeleted = model.xoaHoaDonNhap(maHoaDon);
            if (isDeleted) {
                JOptionPane.showMessageDialog(view, "Xóa hóa đơn nhập thành công!");
                return true;
            } else {
                JOptionPane.showMessageDialog(view, "Không tìm thấy hóa đơn nhập!");
                return false;
            }
        }
        
    }
