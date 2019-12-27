package com.library.repository;

import com.library.model.dto.BookDto;
import com.library.model.jpa.Book;
import com.library.util.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private Logger logger = LoggerFactory.getLogger(BookRepositoryImpl.class);

    private EntityManager eman;

    public BookRepositoryImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        eman = emf.createEntityManager();
    }

    @Override
    public List<BookDto> getAllBooks() {
        logger.error("get all books stuck");
        String sql = "SELECT b FROM Book b";
        Query query = eman.createQuery(sql);
        List<Book> books = query.getResultList();
        return BookMapper.booksListToDtoBooksList(books);
    }

    @Override
    public BookDto getBookById(int id) {
        logger.error("get book by id stuck, id=" + id);
        Book book = eman.find(Book.class, (long) id);
        if (book != null) {
            return BookMapper.bookToDtoBook(book);
        }
        throw new NotFoundException("Not found book with id=" + id);
    }

    @Override
    public void save(BookDto dto) {
        logger.error("save book stuck");
        Book book1 = BookMapper.bookDtoToBook(dto);
        EntityTransaction transaction = eman.getTransaction();
        transaction.begin();
        try {
            if (dto.id == null) {
                eman.persist(book1);
            } else {
                eman.getReference(Book.class, book1.getId());
                eman.merge(book1);
            }
            eman.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new BadRequestException("Book with id " + book1.getId().toString() + " doesn't exist.",
                    Response.status(Response.Status.BAD_REQUEST).build());

        }
    }

    @Override
    public void delete(int id) {
        logger.error("delete book by id stuck, id=" + id);
        Book book = eman.find(Book.class, (long) id);
        if (book == null) {
            throw new NotFoundException("Not found book with id=" + id, Response.status(404).build());
        }
        EntityTransaction transaction = eman.getTransaction();
        transaction.begin();
        eman.remove(book);
        transaction.commit();
    }

}
