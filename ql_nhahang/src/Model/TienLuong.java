package Model;

import java.math.BigDecimal;

public class TienLuong {
    private int MaNhanVien;
    private String TenNhanVien;
    private BigDecimal TongTienLuong;
    private String TinhTrangLuong;
    private int NgayCong;
    private String SoTienThanhToan;
    public int getMaNhanVien() {
        return MaNhanVien;
    }
    public void setMaNhanVien(int maNhanVien) {
        MaNhanVien = maNhanVien;
    }
    public String getTenNhanVien() {
        return TenNhanVien;
    }
    public void setTenNhanVien(String tenNhanVien) {
        TenNhanVien = tenNhanVien;
    }
    public BigDecimal getTongTienLuong() {
        return TongTienLuong;
    }
    public void setTongTienLuong(BigDecimal tongTienLuong) {
        TongTienLuong = tongTienLuong;
    }
    public String getTinhTrangLuong() {
        return TinhTrangLuong;
    }
    public void setTinhTrangLuong(String tinhTrangLuong) {
        TinhTrangLuong = tinhTrangLuong;
    }
    public int getNgayCong() {
        return NgayCong;
    }
    public void setNgayCong(int ngayCong) {
        NgayCong = ngayCong;
    }
    public String getSoTienThanhToan() {
        return SoTienThanhToan;
    }
    public void setSoTienThanhToan(String soTienThanhToan) {
        SoTienThanhToan = soTienThanhToan;
    }
   
    
}
