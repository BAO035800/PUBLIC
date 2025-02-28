package Model;
import java.math.BigDecimal;
public class KhoNguyenLieu {
    private int MaNguyenLieu;
    private String TenNguyenLieu;
    private int MaNhaCungCap;
    private int SoLuong;
    private BigDecimal GiaNHap;
    public int getMaNguyenLieu() {
        return MaNguyenLieu;
    }
    public void setMaNguyenLieu(int maNguyenLieu) {
        MaNguyenLieu = maNguyenLieu;
    }
    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }
    public void setTenNguyenLieu(String tenNguyenLieu) {
        TenNguyenLieu = tenNguyenLieu;
    }
    public int getMaNhaCungCap() {
        return MaNhaCungCap;
    }
    public void setMaNhaCungCap(int maNhaCungCap) {
        MaNhaCungCap = maNhaCungCap;
    }
    public int getSoLuong() {
        return SoLuong;
    }
    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
    public BigDecimal getGiaNHap() {
        return GiaNHap;
    }
    public void setGiaNHap(BigDecimal giaNHap) {
        GiaNHap = giaNHap;
    }
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
