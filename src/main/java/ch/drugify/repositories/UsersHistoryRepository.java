package ch.drugify.repositories;

import ch.drugify.entities.UsersHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface UsersHistoryRepository extends MongoRepository<UsersHistory, String> {
}
