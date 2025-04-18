package model;
import java.util.Date;

public class TonKho {
    private String maNguyenLieu;
    private int soLuongTon;
    private Date ngayCapNhat;
    public String getMaNguyenLieu() {
        return maNguyenLieu;
    }
    public void setMaNguyenLieu(String maNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
    }
    public int getSoLuongTon() {
        return soLuongTon;
    }
    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }
    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    
}
