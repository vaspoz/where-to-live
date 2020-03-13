package ru.vaspoz.relo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logs")
public class AliyahLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String action;
    private String basecountry;
    private String basecity;
    private String comparecountry;
    private String comments;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdatetime;

    public AliyahLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setComparecountry(String comparecountry) {
        this.comparecountry = comparecountry;
    }

    public String getBasecountry() {
        return basecountry;
    }

    public void setBasecountry(String basecountry) {
        this.basecountry = basecountry;
    }

    public String getBasecity() {
        return basecity;
    }

    public void setBasecity(String basecity) {
        this.basecity = basecity;
    }

    public String getComparecountry() {
        return comparecountry;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }
}
