package controller;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import model.Menu;  
import model.MenuDAO;
import view.banhangMenu;
public class MenuController implements KTBanHang {
    private banhangMenu view;
    private MenuDAO model;
    public MenuController(banhangMenu view, MenuDAO model) {
        this.view = view;
        this.model = model;
    }
    public void themMenu() {
        String maMon = view.getTxtMaMon().getText();
        String tenMon = view.getTxtTenMon().getText();
        String giaStr = view.getTxtGia().getText();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString();
        String soLuongStr = view.getTxtSoLuong().getText();
        if (maMon.isEmpty() || tenMon.isEmpty() || giaStr.isEmpty() || tinhTrang.isEmpty() || soLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try{
            BigDecimal gia = new BigDecimal(giaStr);
            int soLuong = Integer.parseInt(soLuongStr);
            Menu menu = new Menu();
            menu.setMaMon(maMon);
            menu.setTenMon(tenMon);
            menu.setGiaTien(gia);
            menu.setTinhTrangMon(tinhTrang);
            menu.setSoLuong(soLuong);
            model.themMenu(menu);
            JOptionPane.showMessageDialog(view, "Thêm món thành công!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm món: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void suaMenu() {
        String maMon = view.getTxtMaMon().getText();
        String tenMon = view.getTxtTenMon().getText();
        String giaStr = view.getTxtGia().getText();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString();
        String soLuongStr = view.getTxtSoLuong().getText();
        if (maMon.isEmpty() || tenMon.isEmpty() || giaStr.isEmpty() || tinhTrang.isEmpty() || soLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try{
            BigDecimal gia = new BigDecimal(giaStr);
            int soLuong = Integer.parseInt(soLuongStr);
            Menu menu = new Menu();
            menu.setMaMon(maMon);
            menu.setTenMon(tenMon);
            menu.setGiaTien(gia);
            menu.setTinhTrangMon(tinhTrang);
            menu.setSoLuong(soLuong);
            model.suaMenu(menu);
            JOptionPane.showMessageDialog(view, "Sửa món thành công!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi sửa món: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void xoaMenu(String maMon) {
        if (maMon.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập mã món cần xóa!");
            return;
        }
        try {
            model.xoaMenu(maMon);
            JOptionPane.showMessageDialog(view, "Xóa món thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa món: " + e.getMessage());
            e.printStackTrace();
        }
    }
}