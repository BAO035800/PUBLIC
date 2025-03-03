package model;
import java.math.BigDecimal;
import java.sql.*;
public class HoaDonNhap {
    private String MaHoaDonKho;
    private String MaNguyenLieu;
    private String MaNhaCungCap;
    private int SoLuong;
    private Date NgayNhap;
    private BigDecimal TongTienNhap;
    public String getMaHoaDonKho() {
        return MaHoaDonKho;
    }
    public void setMaHoaDonKho(String maHoaDonKho) {
        MaHoaDonKho = maHoaDonKho;
    }
    public String getMaNguyenLieu() {
        return MaNguyenLieu;
    }
    public void setMaNguyenLieu(String maNguyenLieu) {
        MaNguyenLieu = maNguyenLieu;
    }
    public String getMaNhaCungCap() {
        return MaNhaCungCap;
    }
    public void setMaNhaCungCap(String maNhaCungCap) {
        MaNhaCungCap = maNhaCungCap;
    }
    public int getSoLuong() {
        return SoLuong;
    }
    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
    public Date getNgayNhap() {
        return NgayNhap;
    }
    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }
    public BigDecimal getTongTienNhap() {
        return TongTienNhap;
    }
    public void setTongTienNhap(BigDecimal tongTienNhap) {
        TongTienNhap = tongTienNhap;
    }
    
    
}
