package com.library.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class BookDto implements Serializable {

    @Null(message = "ID must be null", groups = NewBook.class)
    @NotNull(message = "ID can't be null", groups = UpdateBook.class)
    public Long id;

    public String bookName;

    public BookDto() {
    }

    public BookDto(Long id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }

    public BookDto(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
