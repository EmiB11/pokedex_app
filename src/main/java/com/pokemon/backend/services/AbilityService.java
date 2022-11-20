/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pokemon.backend.service;

import com.pokemon.backend.models.Abilities;

import com.pokemon.backend.repository.AbilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author emidev
 */
public abstract class  AbilityService implements AbilitiesRepository{
    
    @Autowired
    private AbilitiesRepository abilityRepository;
    
    @Override
    public <S extends Abilities> S save(S entity) {
        return abilityRepository.save(entity);
    }
}
