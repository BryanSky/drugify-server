package ch.drugify.server.server;

import ch.drugify.server.entities.Drugs;
import ch.drugify.server.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrugsRepository {
    public static final String DRUGS_COLLECTION_NAME = "drugs";

    @Autowired
    private MongoTemplate mongo;

    public void addDrug(Drugs drug){
        if(!mongo.collectionExists(Drugs.class)){
            mongo.createCollection(Drugs.class);
        }
        mongo.insert(drug, DRUGS_COLLECTION_NAME);
    }

    public Drugs getDrugById(String drugId) {
        Query drug = Query.query(Criteria.where("authNrs").is(drugId));
        Drugs d = mongo.findOne(drug, Drugs.class, DRUGS_COLLECTION_NAME);
        return d;
    }

    public List getAllDrugs() {
        return mongo.findAll(Drugs.class, DRUGS_COLLECTION_NAME);
    }

    public Drugs deleteDrugs(String drugId) {
        Drugs drug = mongo.findOne(Query.query(Criteria.where("authNrs").is(drugId)),
                Drugs.class, DRUGS_COLLECTION_NAME);
        return drug;
    }

    public Drugs updateDrug(String drugId, Drugs drug) {
        Query query = new Query();
        query.addCriteria(Criteria.where("authNrs").is(drugId));

        Update update = new Update();
        //update.set("name", drug.getLastname());

        mongo.updateFirst(query, update, Users.class);

        return drug;
    }
}
