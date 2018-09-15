package ch.drugify.repositories;

import ch.drugify.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface UsersRepository extends MongoRepository<Users, String> {
}
