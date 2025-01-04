package test1;

public class TEST1 {
    private int sodu;
    public int taikhoan;

    public int getTk() {
        return sodu;
    }

    public void setTk(int sodu) {
        this.sodu = sodu;
    }

    public static void main(String[] args) {
        TEST1 taoTk = new TEST1();
        taoTk.setTk(1000);
        System.out.println("Số dư:" + taoTk.getTk());
    }
}
