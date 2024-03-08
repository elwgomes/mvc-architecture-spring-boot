package br.com.elwgomes.application.animal.controller.response;

import br.com.elwgomes.application.animal.domain.Specie;

public record AnimalDTO(String name, Specie specie){
    public AnimalDTO(String name, Specie specie) {
        this.name = name;
        this.specie = specie;
    }

}
