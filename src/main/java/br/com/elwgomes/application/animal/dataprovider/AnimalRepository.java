package br.com.elwgomes.application.animal.dataprovider;

import br.com.elwgomes.application.animal.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
}
