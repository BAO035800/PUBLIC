package model;
import java.math.BigDecimal;
public class Menu{
    private String  MaMon;
    private String TenMon;
    private BigDecimal GiaTien;
    private String TinhTrangMon;
    private int SoLuong;
    
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

    public String getTinhTrangMon() {
        return TinhTrangMon;
    }

    public void setTinhTrangMon(String tinhTrangMon) {
        TinhTrangMon = tinhTrangMon;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public static void main(String[] args)  {
        System.out.println("Hello, world!");
    }
}