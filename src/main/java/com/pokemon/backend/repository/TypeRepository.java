/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pokemon.backend.repository;

import com.pokemon.backend.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author emidev
 */
public interface TypeRepository extends JpaRepository<Type,Long>{
    
    
}
