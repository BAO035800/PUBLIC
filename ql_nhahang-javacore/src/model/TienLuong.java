package model;

import java.math.BigDecimal;

public class TienLuong {
    private String maLuong;
    private String maNhanVien;
    private String tenNhanVien;
    private BigDecimal tongTienLuong;
    private String tinhTrangLuong;
    private int GioLamViec;
    private String GhiChu;

    public String getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(String maLuong) {
        this.maLuong = maLuong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public BigDecimal getTongTienLuong() {
        return tongTienLuong;
    }

    public void setTongTienLuong(BigDecimal tongTienLuong) {
        this.tongTienLuong = tongTienLuong;
    }

    public String getTinhTrangLuong() {
        return tinhTrangLuong;
    }

    public void setTinhTrangLuong(String tinhTrangLuong) {
        this.tinhTrangLuong = tinhTrangLuong;
    }

    public int getGioLamViec() {
        return GioLamViec;
    }

    public void setGioLamViec(int GioLamViec) {
        this.GioLamViec = GioLamViec;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
}
