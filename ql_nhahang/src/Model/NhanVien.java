package model;
import java.math.BigDecimal;
public class NhanVien {
    private String MaNhanVien;
    private String TenNhanVien;
    private int Tuoi;
    private String GioiTinh;
    private String SoDienThoai;
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



    public int getTuoi() {
        return Tuoi;
    }



    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }



    public String getGioiTinh() {
        return GioiTinh;
    }



    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }



    public String getSoDienThoai() {
        return SoDienThoai;
    }



    public void setSoDienThoai(String soDienThoai) {
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
