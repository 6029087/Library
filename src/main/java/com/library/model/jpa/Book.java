package com.library.model.jpa;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book") //, schema = "public"
public class Book {

    @Id
    //@SequenceGenerator(name="SEQ",sequenceName = "seq_book_id",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.IDENTITY) //, generator="SEQ"
    private Long id;

    @Column(name = "book_name", nullable = false)
    @Size(min = 5, message = "Book name must be 5 or greater, but less 100", max=100)
    private String bookName;

    public Book() {
    }

    public Book(Long id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
