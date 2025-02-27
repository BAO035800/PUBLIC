package Model;
public class Menu{
    private int MaMon;
    private String TenMon;
    private float GiaTien;
    private String TinhTrangMon;
    private int SoLuong;
    
    public int getMaMon() {
        return MaMon;
    }

    public void setMaMon(int maMon) {
        MaMon = maMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public float getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(float giaTien) {
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