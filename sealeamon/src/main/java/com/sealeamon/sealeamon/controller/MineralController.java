package com.sealeamon.sealeamon.controller;

import com.sealeamon.sealeamon.model.Mineral;
import com.sealeamon.sealeamon.repository.MineralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/minerals")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true") // 確保跨網域權限
public class MineralController {

    @Autowired
    private MineralRepository mineralRepository;

    // 1. 取得所有礦石
    @GetMapping
    public List<Mineral> getAllMinerals() {
        return mineralRepository.findAll();
    }

    // 2. 新增礦石 (POST)
    @PostMapping
    public Mineral createMineral(@RequestBody Mineral mineral) {
        return mineralRepository.save(mineral);
    }

    // 3. 【核心修復】修改礦石 (PUT) -> 解決 405 錯誤
    // 注意：這裡的 id 型別要跟 Repository 一致，使用 Integer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMineral(@PathVariable Integer id, @RequestBody Mineral mineralDetails) {
        return mineralRepository.findById(id).map(existingMineral -> {
            
            // 更新四語系名稱
            existingMineral.setNameZh(mineralDetails.getNameZh());
            existingMineral.setNameEn(mineralDetails.getNameEn());
            existingMineral.setNameJp(mineralDetails.getNameJp());
            existingMineral.setNameEs(mineralDetails.getNameEs()); // 西語
            
            // 更新其他資訊
            existingMineral.setDescriptionZh(mineralDetails.getDescriptionZh());
            existingMineral.setPrice(mineralDetails.getPrice());
            existingMineral.setImageUrl(mineralDetails.getImageUrl());
            existingMineral.setOrigin(mineralDetails.getOrigin());
            existingMineral.setTags(mineralDetails.getTags());
            
            // 儲存更新後的資料
            mineralRepository.save(existingMineral);
            
            return ResponseEntity.ok("Update Successful");
        }).orElse(ResponseEntity.notFound().build());
    }

    // 4. 刪除礦石 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMineral(@PathVariable Integer id) {
        return mineralRepository.findById(id).map(mineral -> {
            mineralRepository.delete(mineral);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}