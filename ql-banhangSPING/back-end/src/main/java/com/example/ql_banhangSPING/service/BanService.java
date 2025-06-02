package com.example.ql_banhangSPING.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ql_banhangSPING.model.Ban;
import com.example.ql_banhangSPING.repository.BanRepository;

@Service
public class BanService {
    @Autowired
    private BanRepository banRepository;

    // 1. Lấy danh sách tất cả bàn
    public List<Ban> getAllBan() {
        return banRepository.findAll();
    }

    // 2. Lấy bàn theo ID (số bàn)
    public Optional<Ban> getBanById(int soban) {
        return banRepository.findById(soban);
    }

    // 3. Thêm mới hoặc cập nhật bàn
    public Ban saveBan(Ban ban) {
        return banRepository.save(ban);
    }

    // 4. Xóa bàn theo ID
    public void deleteBan(int soban) {
        banRepository.deleteById(soban);
    }
}


