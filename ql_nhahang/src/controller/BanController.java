package controller;

import javax.swing.JOptionPane;
import model.Ban;
import model.BanDAO;
import view.banhangBan;

public class BanController implements KTBanHang {
    private banhangBan view;
    private BanDAO model;

    public BanController(banhangBan view, BanDAO model) {
        this.view = view;
        this.model = model;
    }

    public void themBan() {
        String soBan = view.getTxtBan().getText();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString();
        String ghiChu = view.getTxtGhiChu().getText();
        
        if (soBan.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập số bàn!");
            return;
        }
        
        try {
            int soBanInt = Integer.parseInt(soBan);
            // Kiểm tra bàn đã tồn tại chưa
            if (kiemTraBanTonTai(String.valueOf(soBanInt))) {
                JOptionPane.showMessageDialog(view, "Bàn đã tồn tại!");
                return;
            }
            
            // Kiểm tra giá trị hợp lệ của `TinhTrangBan`
            if (!tinhTrang.equals("Trống") && !tinhTrang.equals("Đã đặt") && !tinhTrang.equals("Đang phục vụ")) {
                JOptionPane.showMessageDialog(view, "Tình trạng bàn không hợp lệ!");
                return;
            }

            Ban ban=new Ban();
            ban.setSoBan(soBanInt);
            ban.setTrangThaiBan(tinhTrang);
            ban.setGhiChu(ghiChu);
            model.themBan(ban);
            System.out.println("TinhTrangBan nhận được: " + tinhTrang);

            JOptionPane.showMessageDialog(view, "Thêm bàn thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số bàn phải là số nguyên!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm bàn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void suaBan() {
        String soBan = view.getTxtBan().getText();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString();
        String ghiChu = view.getTxtGhiChu().getText();

        if (soBan.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập số bàn!");
            return;
        }
        
        try {
            int soBanInt = Integer.parseInt(soBan);
            if (!kiemTraBanTonTai(String.valueOf(soBanInt))) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy bàn!");
                return;
            }
            Ban ban = new Ban(soBanInt, tinhTrang, ghiChu);
            model.suaBan(ban);
            JOptionPane.showMessageDialog(view, "Sửa bàn thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số bàn phải là số nguyên!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi sửa bàn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean xoaBan(int soBan) {
        if (!kiemTraBanTonTai(String.valueOf(soBan))) {
            JOptionPane.showMessageDialog(view, "Không tìm thấy bàn!");
            return false;
        }
        boolean isDeleted = model.xoaBan(soBan);
        if (isDeleted) {
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Không tìm thấy bàn để xóa!");
            return false;
        }
    }
    
}
