package controller;
import java.util.List;

import model.Ban;
import model.BanDAO;

public class BanController {
    BanDAO banDAO = new BanDAO();
    public List<Ban> getBan() {
        return banDAO.getBan();
    }
    public void themBan(Ban ban) {
        banDAO.themBan(ban);
    }
    public void suaBan(Ban ban) {
        banDAO.suaBan(ban);
    }
    public void xoaBan(int SoBan) {
        banDAO.xoaBan(SoBan);
    }
    
}
