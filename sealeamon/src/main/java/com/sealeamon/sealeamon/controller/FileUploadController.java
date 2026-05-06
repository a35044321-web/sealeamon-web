package com.sealeamon.sealeamon.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    // 建議：開發階段先存放在專案根目錄下的 uploads 資料夾
    private final String UPLOAD_DIR = "uploads/";

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) return "";

            // 1. 確保資料夾存在
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 2. 產生唯一檔名
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // 3. 儲存檔案
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 4. 回傳給前端的路徑（之後前台顯示會用到）
            return "uploads/" + fileName;
            
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage(); // 傳回錯誤訊息供前端判斷
        }
    }
}