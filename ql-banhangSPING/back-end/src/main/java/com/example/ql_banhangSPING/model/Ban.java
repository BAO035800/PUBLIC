package com.example.ql_banhangSPING.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ban")
public class Ban {
    @Id
    private int soban;
    private String tinhtrangban;
    private String ghichu;

    public int getSoban() {
        return soban;
    }

    public void setSoban(int soban) {
        this.soban = soban;
    }

    public String getTinhtrangban() {
        return tinhtrangban;
    }

    public void setTinhtrangban(String tinhtrangban) {
        this.tinhtrangban = tinhtrangban;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    public Ban(){}
}
