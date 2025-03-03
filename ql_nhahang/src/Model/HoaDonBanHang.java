package model;
import java.math.BigDecimal;
public class HoaDonBanHang {
    private String MaHoaDonBanHang;
    private String MaMon;
    private String TenMon;
    private BigDecimal GiaTien;
    private int SoBan;
    private BigDecimal TongTienHoaDon;
    private String GhiChu;
    public String getMaHoaDonBanHang() {
        return MaHoaDonBanHang;
    }
    public void setMaHoaDonBanHang(String maHoaDonBanHang) {
        MaHoaDonBanHang = maHoaDonBanHang;
    }
    public String getMaMon() {
        return MaMon;
    }
    public void setMaMon(String maMon) {
        MaMon = maMon;
    }
    public String getTenMon() {
        return TenMon;
    }
    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }
    public BigDecimal getGiaTien() {
        return GiaTien;
    }
    public void setGiaTien(BigDecimal giaTien) {
        GiaTien = giaTien;
    }
    public int getSoBan() {
        return SoBan;
    }
    public void setSoBan(int soBan) {
        SoBan = soBan;
    }
    public BigDecimal getTongTienHoaDon() {
        return TongTienHoaDon;
    }
    public void setTongTienHoaDon(BigDecimal tongTienHoaDon) {
        TongTienHoaDon = tongTienHoaDon;
    }
    public String getGhiChu() {
        return GhiChu;
    }
    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
    
}
