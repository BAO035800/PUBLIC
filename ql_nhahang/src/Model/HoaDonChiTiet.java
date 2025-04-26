package model;
import java.math.BigDecimal;

public class HoaDonChiTiet {
    private String MaMon;
    private String MaHoaDonBanHang;
    private int SoBan;
    private BigDecimal GiaTien;
    private int SoLuongDat;
    private BigDecimal TongTien;
    public String getMaMon() {
        return MaMon;
    }
    public void setMaMon(String maMon) {
        MaMon = maMon;
    }
    public String getMaHoaDonBanHang() {
        return MaHoaDonBanHang;
    }
    public void setMaHoaDonBanHang(String maHoaDonBanHang) {
        MaHoaDonBanHang = maHoaDonBanHang;
    }
    public int getSoBan() {
        return SoBan;
    }
    public void setSoBan(int soBan) {
        SoBan = soBan;
    }
    public BigDecimal getGiaTien() {
        return GiaTien;
    }
    public void setGiaTien(BigDecimal giaTien) {
        GiaTien = giaTien;
    }
    public int getSoLuongDat() {
        return SoLuongDat;
    }
    public void setSoLuongDat(int soLuongDat) {
        SoLuongDat = soLuongDat;
    }
    public BigDecimal getTongTien() {
        return TongTien;
    }
    public void setTongTien(BigDecimal tongTien) {
        TongTien = tongTien;
    }
}
