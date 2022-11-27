package com.pokemon.backend;

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

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class BackendApplication {
         
    @Profile({"dev","prod"})  
    public static void main(String[] args) {
	  ApplicationContext context =   SpringApplication.run(BackendApplication.class, args);
            
           PokemonRepository repoPokemon = context.getBean(PokemonRepository.class);
           AbilitiesRepository repoAbility = context.getBean(AbilitiesRepository.class);
           TypeRepository repoType = context.getBean(TypeRepository.class);
           ImagenesRepository repoImg = context.getBean(ImagenesRepository.class);
           StatRepository repoStat = context.getBean(StatRepository.class);
           
            int pokeID = 1;
            String api = "https://pokeapi.co/api/v2/pokemon/";
            String urlDescription = "https://pokeapi.co/api/v2/pokemon-species/";
            
             while(pokeID <= 10){
            
                Pokemon newPoke = new Pokemon();
                
                Imagenes newImg = new Imagenes();
                
                
                
              JSONObject data = insertDataDB(pokeID ,api);
              JSONObject dataDescription = insertDataDB(pokeID,urlDescription);
              String description = getDescritionApi(dataDescription);
              newPoke.setName(data.getString("name"));
              newPoke.setHeight(data.getInt("height"));
              newPoke.setWeight(data.getLong("weight"));
              newPoke.setSpecies(description);
             
               repoPokemon.save(newPoke);
              List<Abilities> abilities = getAbilitiesApi(data, newPoke, repoAbility);
              
              Imagenes imagen = getImageApi(data, newImg);
              repoImg.save(imagen);
            
              List<Type> types = getTypesApi(data, repoType);
              
              List<Stat> stats = getStatsApi(data, repoStat);
              
              newPoke.setAbilities(abilities);
              newPoke.setSprites(imagen);
              newPoke.setTypes(types);
              newPoke.setStats(stats);
              repoPokemon.save(newPoke);
              
               
              pokeID++;
             }    
	}
    
        public static JSONObject insertDataDB(int idPoke , String api){
            
          String url = api + idPoke;
          JSONObject jsonResponse = null;
          Client client = ClientBuilder.newClient();
          
         
         try{
            WebTarget data = client.target(url);
            String stringResp = data.request(MediaType.APPLICATION_JSON).get(String.class);
            jsonResponse = new JSONObject(stringResp);
              
         }
          catch(JSONException e){
              System.out.println("com.pokemon.backend.BackendApplication.insertDataDB()"); e.getMessage();
          }
           
           
            return jsonResponse;
        }
        
        public static List<Abilities> getAbilitiesApi(JSONObject json,Pokemon pokemon ,AbilitiesRepository repository){
          
            JSONArray  jsonArray = json.getJSONArray("abilities");
            List<Abilities> abilities = new ArrayList<>();
             for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("ability");
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                int slot = jsonObj.getInt("slot");
                 System.out.println(name );
                Abilities newAbility = new Abilities();
                newAbility.setName(name);
                newAbility.setSlot(slot);
               // newAbility.setPokemon(pokemon);
                abilities.add(newAbility);
               repository.save(newAbility);
             }
             
             
             
           return abilities;
        }
        
        public static Imagenes getImageApi(JSONObject json , Imagenes imagen){
            
             JSONObject  jsonObj = json.getJSONObject("sprites");
             String litleImg = jsonObj.getString("front_default");
            jsonObj = jsonObj.getJSONObject("other").getJSONObject("dream_world");
             String bigImg = jsonObj.getString("front_default");
            JSONObject jsonAnimated = json.getJSONObject("sprites").getJSONObject("versions")
                                                                       .getJSONObject("generation-v")
                                                                       .getJSONObject("black-white")
                                                                       .getJSONObject("animated");
            String animated = jsonAnimated.getString("front_default");
            
            imagen.setBackDefault(litleImg);
            imagen.setFrontDefault(bigImg);
            imagen.setVersions(animated);
            
            return imagen;
             
        }
        
        public static List<Type> getTypesApi(JSONObject json ,TypeRepository repository ){
             
            
             
             JSONArray  jsonArray = json.getJSONArray("types");
              List<Type> types = new ArrayList<>();
             
              for(int i = 0 ; i < jsonArray.length(); i++){
                 JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("type");
                  String name = jsonObject.getString("name");
                  
                  
                  Type typesBD = findByName(name, repository);
                  
                  if(typesBD == null){
                      Type newType = new Type();
                      newType.setType(name);
                      types.add(newType);
                     repository.save(newType);
                  }     
                  else{
                     
                      
                      types.add(typesBD);
                  }
                  
              }
              
              return types;
             
        }
        
         public static Type findByName(String name , TypeRepository repository ) {
        
        List<Type> types = repository.findAll();
        
        if(!types.isEmpty()){
            
        for(Type element : types){
            if(name.equals(element.getType())){
                     
                return element;
           }
           
        }
       }
        
        return null;
    }
         
      public static String getDescritionApi(JSONObject api ){
          String description = "";
          JSONArray  jsonArray = api.getJSONArray("flavor_text_entries");
          for(int i = 0 ; i < jsonArray.length() ; i++){
              JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("language");
              String apiDesc = jsonObject.getString("name");
              String jsonText = jsonArray.getJSONObject(i).getString("flavor_text");
              
             
              
              if(apiDesc.equals("es")){
                  description += jsonText + " | ";
              }
            
          }
          
          return description;
          
      }
      
      
      public static List<Stat> getStatsApi(JSONObject json , StatRepository repository){
          
           JSONArray  jsonArray = json.getJSONArray("stats");
              List<Stat> stats = new ArrayList<>();
             
            for(int i = 0 ; i < jsonArray.length(); i++){
                 JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("stat");
                  String name = jsonObject.getString("name");
                  long baseStat =jsonArray.getJSONObject(i).getLong("base_stat");
                 
                  Stat stat = new Stat();
                  stat.setBaseStat(baseStat);
                  stat.setStat(name);
                  stats.add(stat);
                  repository.save(stat);
                
                 
               }
            
                  return stats;
            }

   
      
      
     

}
