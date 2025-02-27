package Model;

public class Ban {
    private int SoBan;
    private String TrangThaiBan;
    private String GhiChu;
    //chua co bang ghi chu
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
