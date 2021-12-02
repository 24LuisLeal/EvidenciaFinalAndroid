package com.tecmilenio.actividad12;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String name;
    private String autor;
    private String category;

    public Book(){

    }

    public Book(int id, String name, String autor, String category) {
        this.id = id;
        this.name = name;
        this.autor = autor;
        this.category = category;
    }

    public Book(String name, String autor, String category) {
        this.name = name;
        this.autor = autor;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", autor='" + autor + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
