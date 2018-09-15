package ch.drugify.server.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UsersHistory {

    @Id
    public ObjectId id;
    @JsonProperty()
    public List<HistoryItem> allItems = new ArrayList<>();

    public String getId() { return id.toHexString(); }
    public void setId(ObjectId id) { this.id = id; }

}
