package ch.drugify.server.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class HistoryItem{

    public HistoryItem(){

    }

    @JsonProperty()
    private String swissMedicId;
    @JsonProperty()
    private Instant created;
    @JsonProperty()
    private Instant updated;
    @JsonProperty
    private long start;
    @JsonProperty
    private long end;
    @JsonProperty
    private String title;

    public String getSwissMedicId() {
        return swissMedicId;
    }

    public void setSwissMedicId(String swissMedicId) {
        this.swissMedicId = swissMedicId;
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

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}