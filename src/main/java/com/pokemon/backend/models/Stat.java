package com.pokemon.backend.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
public class Stat implements Serializable {
    
     @Id
    @Column(name="id_stat")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long baseStat;
     private String stat;
    
    public Stat(){};

    public Stat(long baseStat, String stat) {
        this.baseStat = baseStat;
       
        this.stat = stat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
     
    
    public long getBaseStat() { return baseStat; }
    public void setBaseStat(long value) { this.baseStat = value; }

    public String getStat() { return stat; }
    public void setStat(String value) { this.stat = value; }
}
