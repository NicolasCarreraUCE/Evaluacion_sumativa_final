package com.distribuida.dto;

import com.distribuida.db.Book;

import java.math.BigDecimal;

public class BookDto {

    private Integer id;
    private String isbn;
    private String title;
    private BigDecimal price;

    private String author;

    public static BookDto from(Book obj) {
        BookDto ret = new BookDto();

        ret.setId(obj.getId());
        ret.setIsbn(obj.getIsbn());
        ret.setPrice(obj.getPrice());
        ret.setTitle(obj.getTitle());

        return ret;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
