package model;

public class Ban {
    private int SoBan;
    private String TrangThaiBan;
    private String GhiChu;
    public Ban() {
        super();
    }
    public Ban(int soBan, String trangThaiBan, String ghiChu) {
        super();
        SoBan = soBan;
        TrangThaiBan = trangThaiBan;
        GhiChu = ghiChu;
    }
    public int getSoBan() {
        return SoBan;
    }
    public void setSoBan(int soBan) {
        SoBan = soBan;
    }
    public String getTrangThaiBan() {
        return TrangThaiBan;
    }
    public void setTrangThaiBan(String trangThaiBan) {
        TrangThaiBan = trangThaiBan;
    }
    public String getGhiChu() {
        return GhiChu;
    }
    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
    public static void main(String[] args)  {
        System.out.println("Hello, world!");
    }
}
