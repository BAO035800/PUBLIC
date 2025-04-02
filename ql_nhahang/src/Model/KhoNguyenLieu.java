package model;
import java.math.BigDecimal;
public class KhoNguyenLieu {
    private String MaNguyenLieu;
    private String TenNguyenLieu;
    private String MaNhaCungCap;
    private int SoLuong;
    private BigDecimal GiaNhap;
    
    public KhoNguyenLieu() {
    }
    public String getMaNguyenLieu() {
        return MaNguyenLieu;
    }
    public void setMaNguyenLieu(String maNguyenLieu) {
        MaNguyenLieu = maNguyenLieu;
    }
    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }
    public void setTenNguyenLieu(String tenNguyenLieu) {
        TenNguyenLieu = tenNguyenLieu;
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
    public BigDecimal getGiaNhap() {
        return GiaNhap;
    }
    public void setGiaNhap(BigDecimal giaNhap) {
        GiaNhap = giaNhap;
    }
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
