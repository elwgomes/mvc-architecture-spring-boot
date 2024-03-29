package br.com.elwgomes.application.animal.controller;

import br.com.elwgomes.application.animal.controller.request.AnimalRequest;
import br.com.elwgomes.application.animal.controller.response.AnimalControllerResponse;
import br.com.elwgomes.application.animal.controller.response.AnimalDTO;
import br.com.elwgomes.application.animal.domain.Animal;
import br.com.elwgomes.application.animal.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/{id}")
    public AnimalControllerResponse<Optional<AnimalDTO>> getAnimalById (@PathVariable Long id) {
        Optional<Animal> animal = service.getAnimalById(id);
        Optional<AnimalDTO> animalDTO = Optional.of(new AnimalDTO(animal.get().getName(), animal.get().getSpecie()));
        return new AnimalControllerResponse<>("success", String.valueOf(HttpStatus.OK.value()), animalDTO);
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

    @DeleteMapping("/{id}")
    public AnimalControllerResponse delete (@PathVariable Long id) {
        service.deleteAnimal(id);
        return new AnimalControllerResponse<>("accepted", String.valueOf(HttpStatus.ACCEPTED.value()), "Animal has been deleted.");
    }

    @PutMapping("/{id}")
    public AnimalControllerResponse updateAnimal (@PathVariable Long id, @RequestBody AnimalRequest request) {
        Animal animal = new Animal(null, request.name(), request.specie());
        service.updateAnimal(id, animal);
        return new AnimalControllerResponse<>("success", String.valueOf(HttpStatus.OK.value()), "Animal has been updated.");
    }

    @PatchMapping("/{id}")
    public AnimalControllerResponse updateName (@PathVariable Long id, @RequestBody AnimalRequest request) {
        service.updateAnimalName(id, request.name());
        return new AnimalControllerResponse<>("success", String.valueOf(HttpStatus.OK.value()), "Animal's name has been updated.");
    }
}
