package com.informatorio.myblog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 4)
    private String title;

    @Column(nullable = false)
    @Size(min = 4)
    private String description;

    @Column(nullable = false)
    @Size(min = 4)
    private String content;

    @Column(name = "REGIST_DATE", updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdDate = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));

    @Column(nullable = false)
    private Boolean published;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getPublished() {
        return published;
    }

}
