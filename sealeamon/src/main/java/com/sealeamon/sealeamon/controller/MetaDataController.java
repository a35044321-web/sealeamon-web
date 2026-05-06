package com.sealeamon.sealeamon.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sealeamon.sealeamon.model.*;
import com.sealeamon.sealeamon.service.MetaDataService;

@RestController
@RequestMapping("/api/metadata")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class MetaDataController {

    @Autowired 
    private MetaDataService metaDataService;

    // 1. 取得清單
    @GetMapping("/tags")
    public List<Tag> getTags() { return metaDataService.getAllTags(); }

    @GetMapping("/origins")
    public List<Origin> getOrigins() { return metaDataService.getAllOrigins(); }

    // 2. 新增功能
    @PostMapping("/tags")
    public Tag addTag(@RequestBody Tag tag) { return metaDataService.saveTag(tag); }

    @PostMapping("/origins")
    public Origin addOrigin(@RequestBody Origin origin) { return metaDataService.saveOrigin(origin); }

    // 3. 【核心修復】修改功能 -> 解決 405 錯誤
    @PutMapping("/tags/{id}")
    public ResponseEntity<?> updateTag(@PathVariable Integer id, @RequestBody Tag tagDetails) {
        return metaDataService.findTagById(id).map(tag -> {
            tag.setNameZh(tagDetails.getNameZh());
            metaDataService.saveTag(tag);
            return ResponseEntity.ok("Update Successful");
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/origins/{id}")
    public ResponseEntity<?> updateOrigin(@PathVariable Integer id, @RequestBody Origin originDetails) {
        // 呼叫 Service 的 findOriginById
        return metaDataService.findOriginById(id).map(origin -> {
            origin.setNameZh(originDetails.getNameZh()); // 執行修正
            metaDataService.saveOrigin(origin);           // 存回資料庫
            return ResponseEntity.ok("Update Successful");
        }).orElse(ResponseEntity.notFound().build());
    }

    // 4. 刪除功能
    @DeleteMapping("/tags/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Integer id) {
        metaDataService.deleteTag(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/origins/{id}")
    public ResponseEntity<?> deleteOrigin(@PathVariable Integer id) {
        metaDataService.deleteOrigin(id);
        return ResponseEntity.ok().build();
    }
}