package model;
import java.math.BigDecimal;
import java.sql.*;
public class NhanVien {
    private String MaNhanVien;
    private String TenNhanVien;
    private Date NgaySinh;
    private String GioiTinh;
    private BigDecimal SoDienThoai;
    private String ChucVu;
    private BigDecimal Luong1Gio;
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


    public Date getNgaySinh() {
        return NgaySinh;
    }


    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }


    public String getGioiTinh() {
        return GioiTinh;
    }


    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }


    public BigDecimal getSoDienThoai() {
        return SoDienThoai;
    }


    public void setSoDienThoai(BigDecimal soDienThoai) {
        SoDienThoai = soDienThoai;
    }


    public String getChucVu() {
        return ChucVu;
    }


    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }


    public BigDecimal getLuong1Gio() {
        return Luong1Gio;
    }


    public void setLuong1Gio(BigDecimal luong1Gio) {
        Luong1Gio = luong1Gio;
    }


    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
