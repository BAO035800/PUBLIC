package tuan;

public class SinhVien {
    String maSV;
    String tenSV;
    float dtb;

    public String getTen() {
        String[] name = this.tenSV.split(" ");
        return name[name.length - 1];
    }
}
