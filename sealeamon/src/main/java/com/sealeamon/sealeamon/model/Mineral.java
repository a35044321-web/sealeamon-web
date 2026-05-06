package com.sealeamon.sealeamon.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "minerals")
@Data
public class Mineral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameZh;
    private String nameEn;
    private String nameEs;
    private String nameJp;

    @Column(columnDefinition = "TEXT")
    private String descriptionZh;
    @Column(columnDefinition = "TEXT")
    private String descriptionEn;
    @Column(columnDefinition = "TEXT")
    private String descriptionEs;
    @Column(columnDefinition = "TEXT")
    private String descriptionJp;

    private String price;
    private String size;
    private String imageUrl;
    private String igLink;
    private String status;
    private Integer sortOrder;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;

    @ManyToMany
    @JoinTable(
        name = "mineral_tags",
        joinColumns = @JoinColumn(name = "mineral_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
}