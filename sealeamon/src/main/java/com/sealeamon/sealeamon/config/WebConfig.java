package com.sealeamon.sealeamon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 前後端分離核心：解決 CORS 跨網域問題
     * 讓 VS Code (Port 5500) 可以順利存取 Spring Boot (Port 8080) 的資料
     */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**") 
	            .allowedOrigins(
	                "http://127.0.0.1:5500", 
	                "http://localhost:5500",
	                "https://lemonstonestudio.com",    // 新增你的網域
	                "https://www.lemonstonestudio.com" // 連 www 版本也一起加上去比較保險
	            ) 
	            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	            .allowedHeaders("*")
	            .allowCredentials(true);
	}

    /**
     * 資源路徑映射：將網址路徑對應到實體資料夾
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 1. 動態上傳礦石圖片的路徑 (專案根目錄下的 uploads 資料夾)
        Path uploadDir = Paths.get("uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadPath + "/");

        // 2. 品牌靜態資源 (如 about_us.jpg)
        // 雖然放在 static/resource 下預設會讀取，但為了確保前後端分離時路徑不亂掉，建議明確指定
        registry.addResourceHandler("/resource/**")
                .addResourceLocations("classpath:/static/resource/");

        // 啟動檢查印出
        System.out.println("--- Sealeamon 後端資源映射啟動 ---");
        System.out.println("CORS 允許範圍: http://127.0.0.1:5500");
        System.out.println("Uploads 資料夾映射: file:" + uploadPath + "/");
        System.out.println("--- --- --- --- --- --- --- ---");
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 允許來自你新網域的請求
        configuration.setAllowedOrigins(Arrays.asList(
            "https://lemonstonestudio.com", 
            "https://www.lemonstonestudio.com",
            "http://localhost:8080" // 留下這個方便以後本地測試
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setAllowCredentials(true); // 如果你有用 Cookie/Session，這行很重要
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}