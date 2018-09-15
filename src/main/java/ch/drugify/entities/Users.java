package ch.drugify.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Users {
    @Id()
    public ObjectId id;
    private String firstname;
    private String lastname;
    private String mail;
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
