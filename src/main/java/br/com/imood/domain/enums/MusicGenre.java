package br.com.imood.domain.enums;

import br.com.imood.error.InvalidGenreException;
import lombok.Getter;

import java.util.function.Predicate;

@Getter
public enum MusicGenre {

    PARTY("Party", temp -> temp >= 30),
    POP("Pop", temp -> temp > 15),
    ROCK("Rock", temp -> temp > 10),
    CLASSICAL("Classical", temp -> temp <= 9);

    private String description;
    private Predicate<Double> condition;

    MusicGenre(String description, Predicate<Double> condition){
        this.description = description;
        this.condition = condition;
    }

    public static MusicGenre from(Double temperature) throws InvalidGenreException {
        if(temperature == null){
            return null;
        }
        for(MusicGenre genre : MusicGenre.values()){
            if(genre.getCondition().test(temperature)){
                return genre;
            }
        }
        throw new InvalidGenreException("Music genre not found.");
    }

}
