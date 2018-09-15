package ch.drugify.server.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Users {
    @Id()
    public ObjectId id;
    @JsonProperty()
    private String firstname;
    @JsonProperty()
    private String lastname;
    @JsonProperty()
    private String mail;
    @JsonProperty()
    private String userName;

    public String getId() { return id.toHexString(); }
    public void setId(ObjectId id) { this.id = id; }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}