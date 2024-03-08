package br.com.elwgomes.application.animal.controller;

import br.com.elwgomes.application.animal.controller.request.AnimalRequest;
import br.com.elwgomes.application.animal.controller.response.AnimalControllerResponse;
import br.com.elwgomes.application.animal.controller.response.AnimalDTO;
import br.com.elwgomes.application.animal.domain.Animal;
import br.com.elwgomes.application.animal.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/animal")
@Slf4j
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping
    public AnimalControllerResponse<List<AnimalDTO>> getAllAnimals () {
        List<AnimalDTO> animalResponses = service.getAllAnimals()
                .stream()
                .map(animal -> new AnimalDTO(animal.getName(), animal.getSpecie()))
                .collect(Collectors.toList());
        return new AnimalControllerResponse<>("success", String.valueOf(HttpStatus.OK.value()), animalResponses);
    }

    @PostMapping
    public AnimalControllerResponse create (@RequestBody AnimalRequest request) {
        Animal a = Animal.builder()
                .name(request.name())
                .specie(request.specie())
                .build();
        service.createAnimal(a);
        return new AnimalControllerResponse<>("created", String.valueOf(HttpStatus.CREATED.value()), "Animal has been created.");
    }
}
