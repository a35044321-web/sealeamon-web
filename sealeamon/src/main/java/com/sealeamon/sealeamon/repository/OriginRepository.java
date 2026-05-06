package com.sealeamon.sealeamon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sealeamon.sealeamon.model.Origin;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Integer>{

}
