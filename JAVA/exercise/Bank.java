package exercise;

public class Bank {
    private int soTk;
    private String tenTk;
    private float soduTk;
    private float rutTk;
    private float tien;

    public void setSoTk(int soTk) {
        this.soTk = soTk;
    }

    public int getSoTk() {
        return soTk;
    }

    public void setTenTk(String tenTk) {
        this.tenTk = tenTk;
    }

    public String getTenTk() {
        return tenTk;
    }

    public void setSoduTk(float soduTk) {
        this.soduTk = soduTk;
    }

    public float getSoduTk() {
        return soduTk;
    }

    public void setRutTk(float rutTk) {
        this.rutTk = rutTk;
    }

    public float getRutTk() {
        return rutTk;
    }

    public void setTien(float soduTk, float rutTk) {
        this.tien = soduTk - rutTk;
    }

    public float getTien() {
        return tien;
    }
}