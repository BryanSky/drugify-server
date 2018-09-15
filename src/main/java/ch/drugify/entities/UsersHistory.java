package ch.drugify.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UsersHistory {

    @Id
    public ObjectId id;
    public List<HistoryItem> allItems = new ArrayList<>();

    public String getId() { return id.toHexString(); }
    public void setId(ObjectId id) { this.id = id; }

    public class HistoryItem{
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

        public String drugId;
        public Instant created;
        public Instant updated;
    }
}
