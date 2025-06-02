package com.example.ql_banhangSPING.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ql_banhangSPING.model.Ban;
import com.example.ql_banhangSPING.service.BanService;

@RestController
@RequestMapping("/api/ban")
public class BanController {

    @Autowired
    private BanService banService;

    // GET /api/ban : lấy danh sách tất cả bàn
    @GetMapping
    public List<Ban> getAllBan() {
        return banService.getAllBan();
    }

    // GET /api/ban/{id} : lấy bàn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Ban> getBanById(@PathVariable int id) {
        Optional<Ban> ban = banService.getBanById(id);
        return ban.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/ban : tạo bàn mới
    @PostMapping
    public Ban createBan(@RequestBody Ban ban) {
        return banService.saveBan(ban);
    }

    // PUT /api/ban/{id} : cập nhật bàn
    @PutMapping("/{id}")
    public ResponseEntity<Ban> updateBan(@PathVariable int id, @RequestBody Ban banDetails) {
        Optional<Ban> optionalBan = banService.getBanById(id);
        if (!optionalBan.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Ban ban = optionalBan.get();
        ban.setTinhtrangban(banDetails.getTinhtrangban());
        ban.setGhichu(banDetails.getGhichu());
        Ban updatedBan = banService.saveBan(ban);
        return ResponseEntity.ok(updatedBan);
    }

    // DELETE /api/ban/{id} : xóa bàn
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBan(@PathVariable int id) {
        Optional<Ban> optionalBan = banService.getBanById(id);
        if (!optionalBan.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        banService.deleteBan(id);
        return ResponseEntity.noContent().build();
    }

}
