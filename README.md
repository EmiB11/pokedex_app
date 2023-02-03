
#  Pokemon App

<p align="left">
  <img height="150" src="./pokemon.png" />
</p>

## Objetivos del Proyecto

- Construir una App utlizando Angular, Typescript , Java y Spring.
- Aprender mejores prácticas.
- Aprender y practicar el workflow de GIT.
- Usar y practicar testing.



## Descripción del proyecto

 Es una aplicación en la cual se puedan ver los distintos Pokemon utilizando la api externa [pokeapi](https://pokeapi.co/) guardar los datos mas importantes en la base de datos y a partir de la base de datos creada  poder, entre otras cosas:

  - Buscar pokemons
  - Filtrarlos / Ordenarlos
  - Crear nuevos pokemons
  - Editar los nuevos pokemons creados , no se pueden editar los pokemons existentes.
  - Eliminar los pokemons creados , no se pueden eliminar los pokemons existentes.




#### Backend

Desarrollado con un servidor en Java con Spring con las siguientes rutas:

- [ ] __GET /pokemon__:
  - Obtiene un listado de todos los pokemons de la base de datos.
 
- [ ] __GET /pokemons/{idPokemon}__:
  - Obtiene el detalle de un pokemon en particular
  - trae solo los datos pedidos en la ruta de detalle de pokemon

- [ ] __GET /pokemons?name="..."__:
  - Obtiene el pokemon que coincida exactamente con el nombre pasado como query parameter 
  - Si no existe ningún pokemon muestra un mensaje adecuado

- [ ] __POST /pokemons__:
  - Recibe los datos recolectados desde el formulario controlado de la ruta de creación de pokemons por body
  - Crea un pokemon en la base de datos

- [ ] __GET /types__:
  - Obtiene todos los tipos de pokemons posibles

- [ ] __PUT /pokemons__:
  - Recibe los datos recolectados desde el formulario de edición de pokemons
  - Si el pokemon es uno ya existente y no fue creado se muestra un mensaje de error

- [ ] __DELETE /pokemons__:
  - Elimina el pokemon seleccionado
  - Si el pokemon es uno ya existente y no fue creado se muestra un mensaje de error

  


