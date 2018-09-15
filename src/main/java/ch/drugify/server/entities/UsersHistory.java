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

    public class HistoryItem{

        @JsonProperty()
        public String drugId;
        @JsonProperty()
        public Instant created;
        @JsonProperty()
        public Instant updated;

        public String getDrugId() {
            return drugId;
        }

        public void setDrugId(String drugId) {
            this.drugId = drugId;
        }

        public Instant getCreated() {
            return created;
        }

        public void setCreated(Instant created) {
            this.created = created;
        }

        public Instant getUpdated() {
            return updated;
        }

        public void setUpdated(Instant updated) {
            this.updated = updated;
        }
    }
}
