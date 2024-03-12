package br.com.elwgomes.application.animal.service;

import br.com.elwgomes.application.animal.dataprovider.AnimalRepository;
import br.com.elwgomes.application.animal.domain.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Primary
@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public List<Animal> getAllAnimals () {
        return repository.findAll();
    }

    public Optional<Animal> getAnimalById (Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new NoSuchElementException("Animal not found with id: " + id)));
    }

    public void createAnimal (Animal animal) {
        this.repository.save(animal);
    }

    public void deleteAnimal(Long id) {
        this.repository.deleteById(id);
    }

    public void updateAnimal (Long id, Animal animal) {
        Animal updatedAnimal = getAnimalById(id).orElseThrow(() -> new NoSuchElementException("Animal not found with id: " + id));
        updatedAnimal.setName(animal.getName());
        updatedAnimal.setSpecie(animal.getSpecie());
        createAnimal(updatedAnimal);
    }

    public void updateAnimalName (Long id, String name) {
        Animal updatedAnimal = getAnimalById(id).orElseThrow(() -> new NoSuchElementException("Animal not found with id: " + id));
        updatedAnimal.setName(name);
        createAnimal(updatedAnimal);
    }

}
