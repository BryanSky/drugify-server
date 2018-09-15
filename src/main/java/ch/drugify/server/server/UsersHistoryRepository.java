package ch.drugify.server.server;

import ch.drugify.server.entities.Drugs;
import ch.drugify.server.entities.Users;
import ch.drugify.server.entities.UsersHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersHistoryRepository {

    public static final String USER_HISTORY_COLLECTION_NAME = "user_history";

    @Autowired
    private MongoTemplate mongo;

    public void addUserHistory(UsersHistory userHistory){
        if(!mongo.collectionExists(UsersHistory.class)){
            mongo.createCollection(UsersHistory.class);
        }
        mongo.insert(userHistory, USER_HISTORY_COLLECTION_NAME);
    }

    public UsersHistory getUserHistoryById(String userHistoryId) {
        Query userHistory = Query.query(Criteria.where("id").is(userHistoryId));
        UsersHistory u = mongo.findOne(userHistory, UsersHistory.class, USER_HISTORY_COLLECTION_NAME);
        return u;
    }

    public List getAllUserHistories() {
        return mongo.findAll(UsersHistory.class, USER_HISTORY_COLLECTION_NAME);
    }

    public UsersHistory deleteUserHistory(String historyId) {
        UsersHistory userHistory = mongo.findOne(Query.query(Criteria.where("id").is(historyId)),
                UsersHistory.class, USER_HISTORY_COLLECTION_NAME);
        mongo.remove(userHistory, USER_HISTORY_COLLECTION_NAME);
        return userHistory;
    }

    public UsersHistory updateUserHistory(String id, UsersHistory userHistory) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update();
        //update.set("name", user.getLastname());

        mongo.updateFirst(query, update, UsersHistory.class);

        return userHistory;
    }

    public void updateUserHistory(String id, Drugs drug){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

    }
}
