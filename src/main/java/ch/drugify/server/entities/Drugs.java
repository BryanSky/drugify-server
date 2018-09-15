package ch.drugify.server.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Drugs {
    @Id
    public ObjectId id;

    @JsonProperty()
    private String title;

    @JsonProperty()
    private String authHolder;
    @JsonProperty()
    private String atcCode;
    @JsonProperty()
    private String substances;
    @JsonProperty()
    private String authNrs;
    @JsonProperty()
    private String remark;
    @JsonProperty()
    private String style;
    @JsonProperty()
    private String content;
    @JsonProperty()
    private String type;

    public Drugs(){

    }

    public String getId() { return id.toHexString(); }
    public void setId(ObjectId id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthHolder() {
        return authHolder;
    }

    public void setAuthHolder(String authHolder) {
        this.authHolder = authHolder;
    }

    public String getAtcCode() {
        return atcCode;
    }

    public void setAtcCode(String atcCode) {
        this.atcCode = atcCode;
    }

    public String getSubstances() {
        return substances;
    }

    public void setSubstances(String substances) {
        this.substances = substances;
    }

    public String getAuthNrs() {
        return authNrs;
    }

    public void setAuthNrs(String authNrs) {
        this.authNrs = authNrs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
