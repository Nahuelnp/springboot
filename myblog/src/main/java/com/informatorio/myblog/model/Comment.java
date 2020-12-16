package com.informatorio.myblog.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 1, max = 200)
    private String comment;

    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdDate = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

}
