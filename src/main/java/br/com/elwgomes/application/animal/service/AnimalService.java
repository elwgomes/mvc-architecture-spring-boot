package br.com.elwgomes.application.animal.service;

import br.com.elwgomes.application.animal.dataprovider.AnimalRepository;
import br.com.elwgomes.application.animal.domain.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return repository.findById(id);
    }

    public void createAnimal (Animal animal) {
        this.repository.save(animal);
    }

    public void deleteAnimal(Long id) {
        this.repository.deleteById(id);
    }

}
