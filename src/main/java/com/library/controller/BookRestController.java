package com.library.controller;

import com.library.model.dto.BookDto;
import com.library.model.dto.NewBook;
import com.library.model.dto.UpdateBook;
import com.library.repository.BookRepository;
import com.library.repository.BookRepositoryImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.PostUpdate;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@Api(value = "books")
public class BookRestController {

    @Inject
    private BookRepository repository;

    private Logger logger = LoggerFactory.getLogger(BookRestController.class);

//    public BookRestController() {
//        logger.error("BookRestController works");
//        repository = new BookRepositoryImpl();
//    }

    @GET
    @Path("/")
    @Produces(value = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Return all books")
    public List<BookDto> getAllBook() {
        logger.error("get all book stuck");
        return repository.getAllBooks();
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public void updateBook(@PathParam("id")int id, @Valid @ConvertGroup(from = Default.class, to = UpdateBook.class) BookDto bookDto){
        if ((long)id != bookDto.id) {
            throw new BadRequestException("Path id not equal book id", Response.status(Response.Status.BAD_REQUEST).build());
        }
        repository.save(bookDto);
    }

    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get a book by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Invalid ID supplied"),
            @ApiResponse(code = 200, message = "Get book by ID", response = BookDto.class)})
    public BookDto getBookById(@PathParam("id") int id) {
        logger.error("get book by id stuck, id=" + id);
        return repository.getBookById(id);
    }

    @POST
    @Path("/")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Crate a new book")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Return message with description of error"),
            @ApiResponse(code = 204, message = "A book has added")
    })
    public void saveNewBook(@Valid @ConvertGroup(from = Default.class, to = NewBook.class) BookDto book) {
        logger.error("save a new book stuck");
        repository.save(book);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete a book by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted"),
           @ApiResponse(code = 404, message = "Invalid ID supplied")
    })
    public void delete(@PathParam("id") int id) {
        logger.error("delete book by id stuck, id=" + id);
        repository.delete(id);
    }

}
