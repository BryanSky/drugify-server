package ch.drugify.storage;

import ch.drugify.entities.Users;

public class EntityManager {

    public Users getUserById(String id){
        return new Users();
    }
}
