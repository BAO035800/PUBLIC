package exercise.kethua;

public class nhanvienfulltime extends nhanvien {
    private double heso;
    private double luongCoBan;

    public double getHeso() {
        return heso;
    }

    public void setHeso(double heso) {
        this.heso = heso;
    }

    public double tinhLuong(double heso, double luongCoban) {
        return heso * luongCoban;
    }

    @Override
    public void hienThithongtin(String ten, int tuoi) {
        super.hienThithongtin(ten, tuoi);
        System.out.println("Lương nhân viên full time:" + tinhLuong(heso, luongCoBan));
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }
}
