package com.pokemon.backend.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
public class Imagenes implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    private String backDefault;
    private String frontDefault;
   
    private String animated;

    public Imagenes(){}

    public Imagenes(String backDefault, String frontDefault, String versions) {
        this.backDefault = backDefault;
        this.frontDefault = frontDefault;
        this.animated = versions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getBackDefault() {
        return backDefault;
    }
    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }
    public String getFrontDefault() {
        return frontDefault;
    }
    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }
   
    public String getVersions() {
        return animated;
    }
    public void setVersions(String versions) {
        this.animated = versions;
    }

    
   
}
