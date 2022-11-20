package com.pokemon.backend.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
public class Abilities implements Serializable {
    
    @Id
    @Column(name="id_ability")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    
    private String name;
    private int slot;
    
   /* @ManyToOne
    @JoinColumn(name="id_pokemon",referencedColumnName="id_pokemon")
    private Pokemon pokemon;*/
    
      public Abilities(){}

    public Abilities( String name, int slot) {
        
        this.name = name;
        this.slot = slot;
        //this.pokemon = pokemon;
    }

    /*public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }*/
      
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    
}
