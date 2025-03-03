package model;

import java.math.BigDecimal;

public class TienLuong {
    private String MaLuong;
    private String MaNhanVien;
    private String TenNhanVien;
    private BigDecimal TongTienLuong;
    private String TinhTrangLuong;
    private int NgayCong;
    private BigDecimal SoTienThanhToan;
    public String getMaLuong() {
        return MaLuong;
    }
    public void setMaLuong(String maLuong) {
        MaLuong = maLuong;
    }
    public String getMaNhanVien() {
        return MaNhanVien;
    }
    public void setMaNhanVien(String maNhanVien) {
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
    public BigDecimal getSoTienThanhToan() {
        return SoTienThanhToan;
    }
    public void setSoTienThanhToan(BigDecimal soTienThanhToan) {
        SoTienThanhToan = soTienThanhToan;
    }
    
}
