/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pokemon.backend.controller;

import com.pokemon.backend.models.Abilities;
import com.pokemon.backend.models.Imagenes;
import com.pokemon.backend.models.Pokemon;
import com.pokemon.backend.models.Stat;
import com.pokemon.backend.models.Type;
import com.pokemon.backend.repository.AbilitiesRepository;
import com.pokemon.backend.repository.ImagenesRepository;
import com.pokemon.backend.repository.PokemonRepository;
import com.pokemon.backend.repository.StatRepository;
import com.pokemon.backend.repository.TypeRepository;
import com.pokemon.backend.service.PokemonService;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;

/**
 *
 * @author emidev
 */
@Component
@Path("/")
public class ControllerPokemon {
    
      
    private final PokemonRepository pokemonService;
    private final ImagenesRepository imagenService;
    private final StatRepository statService;
    private final AbilitiesRepository abilityService;
    private final TypeRepository typeService;

    public ControllerPokemon(PokemonRepository pokemonService, ImagenesRepository imagenService, StatRepository statService, AbilitiesRepository abilityService , TypeRepository typeService) {
        this.pokemonService = pokemonService;
        this.imagenService = imagenService;
        this.statService = statService;
        this.abilityService = abilityService;
        this.typeService = typeService;
    }
    
    @GET
    @Path("/pokemon")
    @Produces("application/json")
    public ResponseEntity<List> getPokemon(@QueryParam("name") String name){
           List<Pokemon> listPokemon = pokemonService.findAll();
           List<HashMap> response = new ArrayList<>();
           
           if(name == null){
          for(Pokemon pokemon : listPokemon){
               HashMap<String ,Object> apiPokemon = new HashMap<>();
              apiPokemon.put("name", pokemon.getName());
              apiPokemon.put("id", pokemon.getID());
              apiPokemon.put("height", pokemon.getHeight());
              apiPokemon.put("Weight", pokemon.getWeight());
              apiPokemon.put("type", pokemon.getTypes());
              apiPokemon.put("image", pokemon.getSprites().getFrontDefault());
              apiPokemon.put("image_animated", pokemon.getSprites().getVersions());
             response.add(apiPokemon);
          }
          System.out.println(response);
          return ResponseEntity.ok(response);
         }
          for(Pokemon pokemon : listPokemon){
               if(pokemon.getName().equalsIgnoreCase(name)){
                  HashMap<String ,Object> apiPokemon = new HashMap<>();
                  apiPokemon.put("pokemon",pokemon);
                  response.add(apiPokemon);
                return ResponseEntity.ok(response);
            }
          }
          return ResponseEntity.notFound().build();
    }
    
    @GET
    @Path("/pokemon/page/{numPage}")
    @Produces("application/json")
    public ResponseEntity<List> getPagePokemon(@PathParam("numPage") int numPage ){
         List<Pokemon> listPokemon = pokemonService.findAll();
        
         int enIndx = 5 * numPage;
         int stIndx = enIndx - 5;
         
         if(enIndx > listPokemon.size()) {
             return ResponseEntity.badRequest().build();
         }
         
        List<Pokemon> sliceList = listPokemon.subList(stIndx, enIndx);
        List<HashMap> response = new ArrayList<>();
        
        for(Pokemon pokemon : sliceList){
               HashMap<String ,Object> apiPokemon = new HashMap<>();
              apiPokemon.put("name", pokemon.getName());
              apiPokemon.put("id", pokemon.getID());
              apiPokemon.put("height", pokemon.getHeight());
              apiPokemon.put("Weight", pokemon.getWeight());
              apiPokemon.put("type", pokemon.getTypes());
              apiPokemon.put("image", pokemon.getSprites().getFrontDefault());
              apiPokemon.put("image_animated", pokemon.getSprites().getVersions());
             response.add(apiPokemon);
          }
          System.out.println(response);
          return ResponseEntity.ok(response);
          
    }
    
    @GET
    @Path("/pokemon/{id}")
    @Produces("application/json")
    public ResponseEntity<Pokemon> getOnePokemon(@PathParam("id") Long id ){
        Optional<Pokemon> pokemon = pokemonService.findById(id);
        
        if(id == null){
            
            return ResponseEntity.badRequest().build();
        }
        if(pokemon.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(pokemon.get());
    }
    
    @POST
    @Path("/pokemon")
    @Produces("application/json")
    public ResponseEntity<String> createPokemon(Pokemon pokemon ){
     
      
       try{  
      
      if(pokemon != null){
      
       imagenService.save(pokemon.getSprites());
      
       
       for(Abilities ability : pokemon.getAbilities()){
           abilityService.save(ability);
           
       }
       
       for(Stat stat : pokemon.getStats()){
           statService.save(stat);
       }
       
       
       pokemonService.save(pokemon);
       return ResponseEntity.ok("El Pokemon Se creó correctamente");
       
       }
       
       return ResponseEntity.badRequest().build();
       
    }catch(Exception e){
        return ResponseEntity.internalServerError().build();
    }
    
  }
    
    
   @PUT
   @Path("/pokemon/update/{id}")
   @Produces("application/json")
   public ResponseEntity<String> updatePokemon(@PathParam("id") Long id , Pokemon pokemon){
      
       if(id == null){
           return ResponseEntity.badRequest().build();
       }
       
       Optional<Pokemon> updatePokemon = pokemonService.findById(id);
       
       if(updatePokemon.isEmpty()){
           return ResponseEntity.notFound().build();
       }
      
       try{
           
         imagenService.save(pokemon.getSprites());
      
       
       for(Abilities ability : pokemon.getAbilities()){
           abilityService.save(ability);
           
       }
       
       for(Stat stat : pokemon.getStats()){
           statService.save(stat);
       }
       
       
      pokemonService.save(pokemon);
       
       return ResponseEntity.ok("El Pokemon Se actualizó correctamente");
           
       }catch(Exception e){
           return ResponseEntity.internalServerError().build();
       }
       
   }
    
    
   @DELETE
   @Path("/pokemon/delete/{id}")
   @Produces("application/json")
   public ResponseEntity<String> deletePokemon(@PathParam("id") Long id ){
       
        if(id == null){
           return ResponseEntity.badRequest().build();
       }
       
       pokemonService.deleteById(id);
       return ResponseEntity.ok("El pokemón se eliminó correctamente");
   }
   
   @GET
   @Path("/types")
   @Produces("application/json")
   public ResponseEntity<List> getTypes(){
       List<Type> types = typeService.findAll();
       
       return ResponseEntity.ok(types);
   }
}
