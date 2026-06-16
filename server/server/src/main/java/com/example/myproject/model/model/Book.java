package com.example.myproject.model.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String image;
    private String summery;
    private int pageCount;

    @ManyToOne
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Lend>  lendList;
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Comment> listCommend;

    @JsonCreator
    public Book() {
    }

    public Book(String title, Long id, String author, String image, String summery, int pageCount) {
        this.title = title;
        this.id = id;
        this.author = author;
        this.image = image;
        this.summery = summery;
        this.pageCount = pageCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Lend> getLendList() {
        return lendList;
    }

    public void setLendList(List<Lend> lendList) {
        this.lendList = lendList;
    }

    public List<Comment> getListCommend() {
        return listCommend;
    }

    public void setListCommend(List<Comment> listCommend) {
        this.listCommend = listCommend;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
