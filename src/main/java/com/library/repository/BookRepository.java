package com.library.repository;

import com.library.model.dto.BookDto;
import com.library.model.jpa.Book;

import java.util.List;

public interface BookRepository {
    List<BookDto> getAllBooks();
    BookDto getBookById(int id);
    void save(BookDto dto);
    void delete(int id);
}
