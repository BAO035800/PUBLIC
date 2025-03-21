package controller;
import model.HoaDonBanHangDAO;
import view.banhangHoaDonBanHang;

public class HDBanHangController implements KTBanHang {
    private banhangHoaDonBanHang view;
    private HoaDonBanHangDAO model;
    public HDBanHangController(banhangHoaDonBanHang view, HoaDonBanHangDAO model) {
        this.view = view;
        this.model = model;
    }
}
