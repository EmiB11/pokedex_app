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
public class Type implements Serializable {
    @Id
    @Column(name="id_type")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String type;
    
    public Type(){}

    public Type(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }
}
