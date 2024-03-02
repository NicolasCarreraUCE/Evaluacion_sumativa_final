package com.distribuida.rest;

import com.distribuida.client.AuthorRestClient;
import com.distribuida.db.Book;
import com.distribuida.dto.BookDto;
import com.distribuida.repo.BookRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BookRest {
    @Inject
    private BookRepository repository;

    @Inject
    @RestClient
    private AuthorRestClient authorClient;

    @GET
    @Operation(summary = "Obtener todos los libros", description = "Devuelve una lista con todos los libros almacenados en la base de datos")
    public List<BookDto> findAll() {
        return repository.findAll().stream().map(book -> {
            var author = authorClient.findById(book.getAuthorId());
            var newBook = BookDto.from(book);
            String nameAuthor = String.format("%s %s", author.getLastName(), author.getFirstName());
            return  newBook;
        }).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Obtener un libro por ID", description = "Devuelve un libro especifico segun el ID proporcionado")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "200", description = "Libro encontrado"),
                    @APIResponse(responseCode = "404", description = "Libro no encontrado")
            }
    )
    public Response findById(@PathParam("id") Integer id) {
        var book = repository.findById(id);
        if(book != null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }

    @POST
    @Operation(summary = "Agregar un libro", description = "Agrega un nuevo libro a la base de datos")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "201", description = "Libro creado exitosamente")
            }
    )
    public Response create(Book obj) {
        repository.save(obj);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar un libro", description = "Actualiza la información de un libro existente en la base de datos")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "204", description = "Usuario actualizado exitosamente"),
                    @APIResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    public Response update(@PathParam("id")Integer id, Book obj) {
        obj.setId(id);
        repository.save(obj);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar un libro", description = "Elimina un libro de la base de datos")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "204", description = "Libro eliminado exitosamente"),
                    @APIResponse(responseCode = "404", description = "Libro no encontrado")
            }
    )
    public Response delete(@PathParam("id")Integer id) {
        repository.delete(id);
        return Response.ok().build();
    }
}
