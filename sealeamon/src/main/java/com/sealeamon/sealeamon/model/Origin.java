package com.sealeamon.sealeamon.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="origins")
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_zh") // 強制對應資料庫裡的 name_zh
    private String nameZh;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "name_es")
    private String nameEs;

    @Column(name = "name_jp")
    private String nameJp;
}