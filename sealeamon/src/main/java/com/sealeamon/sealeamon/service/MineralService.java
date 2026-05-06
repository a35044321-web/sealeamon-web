package com.sealeamon.sealeamon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sealeamon.sealeamon.model.Mineral;
import com.sealeamon.sealeamon.repository.MineralRepository;

@Service
public class MineralService {
	@Autowired
    private MineralRepository mineralRepository;

    public List<Mineral> findAll() {
        return mineralRepository.findAll();
    }

    public Mineral findById(Integer id) {
        return mineralRepository.findById(id).orElse(null);
    }

    public Mineral save(Mineral mineral) {
        return mineralRepository.save(mineral);
    }

    public void delete(Integer id) {
        mineralRepository.deleteById(id);
    }
}
