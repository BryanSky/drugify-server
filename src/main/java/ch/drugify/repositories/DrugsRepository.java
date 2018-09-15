package ch.drugify.repositories;

import ch.drugify.entities.Drugs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface DrugsRepository extends MongoRepository<Drugs, String> {
}
