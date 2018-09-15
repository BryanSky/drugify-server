package ch.drugify.server.server;

import ch.drugify.server.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersRepository {

    public static final String USER_COLLECTION_NAME = "users";

    @Autowired
    private MongoTemplate mongo;

    public void addUser(Users user){
        if(!mongo.collectionExists(Users.class)){
            mongo.createCollection(Users.class);
        }
        mongo.insert(user, USER_COLLECTION_NAME);
    }

    public Users getUserById(String userId) {
        Query user = Query.query(Criteria.where("id").is(userId));
        Users u = mongo.findOne(user, Users.class, USER_COLLECTION_NAME);
        return u;
    }

    public List getAllUsers() {
        return mongo.findAll(Users.class, USER_COLLECTION_NAME);
    }

    public Users deleteUser(String userId) {
        Users user = mongo.findOne(Query.query(Criteria.where("id").is(userId)), Users.class, USER_COLLECTION_NAME);
        mongo.remove(user, USER_COLLECTION_NAME);

        return user;
    }

    public Users updateUser(String name, Users user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastname").is(name));

        Update update = new Update();
        update.set("name", user.getLastname());

        mongo.updateFirst(query, update, Users.class);

        return user;
    }
}