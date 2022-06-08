package ch.bzz.pokemon.service;

import ch.bzz.pokemon.data.DataHandler;
import ch.bzz.pokemon.model.Pokemon;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;



@Path("pokemon")
public class PokemonService {
    /**
     * reads a list of all pokemons
     * @return list of all pokemons
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPokemons(){
        List<Pokemon> pokemonList = DataHandler.readAllPokemons();
        return Response
                .status(200)
                .entity(pokemonList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPokemon(
            @QueryParam("id") String pokemonID
    ){
        Pokemon pokemon = DataHandler.readPokemonByID(pokemonID);
        int httpsStatus;
        if (pokemon == null){
            httpsStatus = 404;
        }else {
            httpsStatus = 288;
        }
        return Response
                .status(httpsStatus)
                .entity(pokemon)
                .build();
    }

    /**
     *
     * @return Response
     */

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertPokemon(
            @Valid @BeanParam Pokemon pokemon,
            @FormParam("typID") String typID
    ){

        pokemon.setTypID(typID);
        DataHandler.insertPokemon(pokemon);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     *
     * @return Response
     */

    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePokemon(
            @Valid @BeanParam Pokemon pokemon,
            @FormParam("typID") String typID
    ){
        int httpStatus = 200;
        Pokemon oldPokemon = DataHandler.readPokemonByID(pokemon.getPokemonID());
        if (oldPokemon != null){
            oldPokemon.setName(pokemon.getName());
            oldPokemon.setGroesse(pokemon.getGroesse());
            oldPokemon.setMegaEvolution(pokemon.isMegaEvolution());
            oldPokemon.setTrainerID(pokemon.getTrainerID());
            oldPokemon.setTypID(pokemon.getTyp().getTypID());

            DataHandler.updatePokemon();
        }else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * delets a pokemon indentified by its uuid
     * @param pokemonID the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePokemon(
            @QueryParam("id") String pokemonID
    ){
        int httpStatus = 200;
        if (!DataHandler.deletePokemon(pokemonID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}

