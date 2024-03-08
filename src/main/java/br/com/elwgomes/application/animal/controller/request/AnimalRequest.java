package br.com.elwgomes.application.animal.controller.request;

import br.com.elwgomes.application.animal.domain.Specie;

public record AnimalRequest(String name, Specie specie) {
}
