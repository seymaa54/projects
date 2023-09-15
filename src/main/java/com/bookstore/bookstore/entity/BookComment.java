package com.bookstore.bookstore.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BookComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentText;

    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;
    @PrePersist
    protected void onCreate() {
        commentDate = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
