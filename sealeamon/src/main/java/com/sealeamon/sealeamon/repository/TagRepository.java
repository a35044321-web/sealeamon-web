package com.sealeamon.sealeamon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sealeamon.sealeamon.model.Tag;
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>{

}
