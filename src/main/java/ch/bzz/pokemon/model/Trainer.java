package ch.bzz.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.List;

/**
 * a pokemon trainer
 */
public class Trainer {
    @JsonIgnore
    private List<Pokemon> pokemonList;

    @FormParam("trainerUUID")
    @Pattern(regexp = "ID-\\d{1,2}")
    @NotEmpty
    private String trainerUUID;
    @FormParam("trainer")
    @NotEmpty
    @Size(min=2, max=10)
    private String trainer;
    @FormParam("ort")
    @NotEmpty
    @Size(min=2, max=20)
    private String ort;

    /**
     * default constructor
     */
    public Trainer() {
        setPokemonList(new ArrayList<>());
    }
    /**
     * gets trainer
     *
     * @return value of trainer
     */
    public String getTrainer() {
        return trainer;
    }

    /**
     * sets trainer
     *
     * @param trainer the value to set
     */

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    /**
     * gets trainerID
     *
     * @return value of trainerID
     */
    public String getTrainerUUID() {
        return trainerUUID;
    }

    /**
     * sets trainerID
     *
     * @param trainerUUID the value to set
     */

    public void setTrainerUUID(String trainerUUID) {
        this.trainerUUID = trainerUUID;
    }

    /**
     * gets ort
     *
     * @return value of ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * sets ort
     *
     * @param ort the value to set
     */

    public void setOrt(String ort) {
        this.ort = ort;
    }


    /**
     * gets pokemonList
     *
     * @return value of pokemonList
     */

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    /**
     * sets pokemonList
     *
     * @param pokemonList the value to set
     */

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }


}
