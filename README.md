# Lemon Stone Studio - 檸檬石工作室 (畢業專題)

這是一個專為礦石展示設計的 Web 平台，旨在提供優質的礦石科普知識，並透過 RWD 響應式設計引導使用者至 Instagram 進行社群互動與購買。

## 🚀 專案亮點
* **全端開發**：整合 Java Spring Boot 後端與 MySQL 資料庫。
* **雲端佈署**：運行於 Oracle Cloud Ubuntu 伺服器，並使用 Nginx 進行反向代理。
* **安全防護**：導入 SSL (HTTPS) 加密憑證，並透過 Cloudflare 進行 DNS 管理與安全性強化。
* **行動優先**：針對手機用戶優化，支援大檔案圖片上傳與 RWD 瀏覽體驗。

## 🛠️ 技術棧 (Tech Stack)
* **Backend:** Java 17, Spring Boot 3, Spring Security
* **Database:** MySQL 8.0
* **Frontend:** HTML5, CSS3, JavaScript (Vanilla JS)
* **Web Server:** Nginx
* **Infrastructure:** Oracle Cloud Infrastructure (OCI), Cloudflare, SSL/TLS (Certbot)

## 📦 核心功能
1. **礦石展示系統**：動態載入資料庫礦石資料，支援 RWD 排版。
2. **後台管理介面**：提供管理員登入，支援手機直接拍照上傳礦石照片。
3. **IG 導流連結**：點擊展示頁面可直接跳轉至對應的 Instagram 貼文。
4. **科普文章系統**：發布礦物相關知識，提升網站 SEO 表現。

## 🔧 本地開發設定
1. Clone 本專案。
2. 參考 `src/main/resources/application.properties.example` 建立你自己的 `application.properties`。
3. 確保本地環境已安裝 JDK 17 與 MySQL。
4. 使用 Maven 進行編譯與運行。
