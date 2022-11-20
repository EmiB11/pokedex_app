/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pokemon.backend.service;

import com.pokemon.backend.models.Imagenes;
import com.pokemon.backend.repository.ImagenesRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author emidev
 */
public abstract class ImagenService implements ImagenesRepository {
    
    @Autowired
    private ImagenesRepository imagenRepository;
    
    @Override
    public <S extends Imagenes> S save(S entity) {
       return imagenRepository.save(entity);
    }
}
