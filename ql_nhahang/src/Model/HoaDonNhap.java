package Model;
import java.math.BigDecimal;
public class HoaDonNhap {
    private int MaHoaDonKho;
    private int MaNguyenLieu;
    private int SoLuong;
    private String NgayNhap;
    private BigDecimal TongTienNhap;
    public int getMaHoaDonKho() {
        return MaHoaDonKho;
    }
    public void setMaHoaDonKho(int maHoaDonKho) {
        MaHoaDonKho = maHoaDonKho;
    }
    public int getMaNguyenLieu() {
        return MaNguyenLieu;
    }
    public void setMaNguyenLieu(int maNguyenLieu) {
        MaNguyenLieu = maNguyenLieu;
    }
    public int getSoLuong() {
        return SoLuong;
    }
    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
    public String getNgayNhap() {
        return NgayNhap;
    }
    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
    }
    public BigDecimal getTongTienNhap() {
        return TongTienNhap;
    }
    public void setTongTienNhap(BigDecimal tongTienNhap) {
        TongTienNhap = tongTienNhap;
    }
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
