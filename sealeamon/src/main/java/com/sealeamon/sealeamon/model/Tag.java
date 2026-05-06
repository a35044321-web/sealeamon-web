package com.sealeamon.sealeamon.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="tags")
@Data
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameZh;
    private String nameEn;
    private String nameEs;
    private String nameJp;
	
    
}
