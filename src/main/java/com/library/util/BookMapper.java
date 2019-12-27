package com.library.util;

import com.library.model.dto.BookDto;
import com.library.model.jpa.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static List<BookDto> booksListToDtoBooksList(List<Book> source) {
        List<BookDto> dest = new ArrayList<>();
        source.forEach(book -> {
            dest.add(BookMapper.bookToDtoBook(book));
        });
        return dest;
    }

    public static BookDto bookToDtoBook(Book book) {
        return new BookDto(book.getId(), book.getBookName());
    }

    public static Book bookDtoToBook(BookDto dto) {
        return new Book(dto.id, dto.bookName);
    }

}
