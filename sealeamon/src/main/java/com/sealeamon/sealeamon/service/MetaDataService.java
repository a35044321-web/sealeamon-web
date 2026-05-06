package com.sealeamon.sealeamon.service;

import java.util.List;
import java.util.Optional; // 必須引入，用於處理 PUT 邏輯中的查詢

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sealeamon.sealeamon.model.Origin;
import com.sealeamon.sealeamon.model.Tag;
import com.sealeamon.sealeamon.repository.OriginRepository;
import com.sealeamon.sealeamon.repository.TagRepository;

/**
 * 地質元數據服務 (Geological Metadata Service)
 * 負責處理產地 (Locality) 與 科學標籤 (Scientific Tags) 的業務邏輯
 */
@Service
public class MetaDataService {

    @Autowired 
    private TagRepository tagRepository;
    
    @Autowired 
    private OriginRepository originRepository;
    
    // --- 科學標籤 (Tag) 管理 ---
    
    public List<Tag> getAllTags() {
        return tagRepository.findAll(); 
    }
    
    // 用於修改功能：先根據 ID 找到對應的標籤
    public Optional<Tag> findTagById(Integer id) {
        return tagRepository.findById(id);
    }
    
    public Tag saveTag(Tag tag) { 
        return tagRepository.save(tag); 
    }
    
    public void deleteTag(Integer id) { 
        tagRepository.deleteById(id); 
    }

    // --- 產地 (Origin / Locality) 管理 ---
    
    public List<Origin> getAllOrigins() {
        return originRepository.findAll();
    }
    
    // 用於修改功能：先根據 ID 找到對應的產地
    public Optional<Origin> findOriginById(Integer id) {
        return originRepository.findById(id);
    }
    
    public Origin saveOrigin(Origin origin) { 
        return originRepository.save(origin); 
    }
    
    public void deleteOrigin(Integer id) { 
        originRepository.deleteById(id); 
    }
}