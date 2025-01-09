package exercise.kethua;

public class nhanvienparttime extends nhanvien {
    private int giolam;
    private double luongtheogio;

    public int getGiolam() {
        return giolam;
    }

    public void setGiolam(int giolam) {
        this.giolam = giolam;
    }

    public double getLuongtheogio() {
        return luongtheogio;
    }

    public void setLuongtheogio(double luongtheogio) {
        this.luongtheogio = luongtheogio;
    }

    public void tinhLuong() {
        System.out.println("Lương nhân viên part time: " + giolam * luongtheogio);
    }

    @Override
    public void hienThithongtin(String ten, int tuoi) {
        super.hienThithongtin(ten, tuoi);
        System.out.println("Giờ làm: " + giolam);
        System.out.println("Lương theo giờ: " + luongtheogio);
        tinhLuong();
    }
}
