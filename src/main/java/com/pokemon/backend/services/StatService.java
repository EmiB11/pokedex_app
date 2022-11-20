/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pokemon.backend.service;

import com.pokemon.backend.models.Pokemon;
import com.pokemon.backend.models.Stat;
import com.pokemon.backend.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author emidev
 */
public abstract class StatService implements StatRepository {
    
    @Autowired
    private StatRepository statRepository;
    
    @Override
    public <S extends Stat> S save(S entity) {
        return statRepository.save(entity);
    }
}
