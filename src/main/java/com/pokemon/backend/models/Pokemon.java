package com.pokemon.backend.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;



@Entity
@Proxy(lazy=false)
public class Pokemon implements Serializable {
    @Id
    @Column(name="id_pokemon")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
  
   
    private long weight;
    private long height;
    private String name;
    
    @Column(name="description" , columnDefinition="TEXT")
    private String species;
    
    @OneToOne
    private Imagenes sprites;
    
     @OneToMany(fetch = FetchType.EAGER , targetEntity = Abilities.class)
     @Fetch(value = FetchMode.SUBSELECT)
    private List<Abilities> abilities = new ArrayList<Abilities>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "stats_pokemon" ,
            joinColumns = @JoinColumn(name="id_pokemon"),
            inverseJoinColumns = @JoinColumn(name="id_stat"))
    private List<Stat> stats;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "types_pokemon" ,
            joinColumns = @JoinColumn(name="id_pokemon"),
            inverseJoinColumns = @JoinColumn(name="id_type"))
    private List<Type> types;
    
   
     
    public Pokemon() {}

    public List<Abilities> getAbilities() { return abilities; }
    public void setAbilities(List<Abilities> value) { this.abilities = value; }

    public long getHeight() { return height; }
    public void setHeight(long value) { this.height = value; }

    public Long getID() { return id; }
    public void setID(Long value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getSpecies() { return species; }
    public void setSpecies(String value) { this.species = value; }

    public  Imagenes getSprites() { return sprites; }
    public void setSprites(Imagenes value) { this.sprites = value; }

    public List<Stat> getStats() { return stats; }
    public void setStats(List<Stat> value) { this.stats = value; }

    public List<Type> getTypes() { return types; }
    public void setTypes(List<Type> value) { this.types = value; }

    public long getWeight() { return weight; }
    public void setWeight(long value) { this.weight = value; }
}

