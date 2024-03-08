package br.com.elwgomes.application.animal.config;

import br.com.elwgomes.application.animal.dataprovider.AnimalRepository;
import br.com.elwgomes.application.animal.domain.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

import static br.com.elwgomes.application.animal.domain.Specie.*;

@Configuration
@Profile("test")
public class AnimalConfiguration implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public void run(String... args) throws Exception {
        Animal a1 = new Animal(null, "Gato", CAT);
        Animal a2 = new Animal(null, "Cachorro", DOG);
        Animal a3 = new Animal(null, "Horse", HORSE);
        animalRepository.saveAll(Arrays.asList(a1, a2, a3));
    }
}
