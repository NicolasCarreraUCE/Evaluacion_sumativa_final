package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.repo.AuthorRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AuthorRest {
    @Inject
    private AuthorRepository repository;

    @GET
    @Operation(summary = "Obtener todos los autores", description = "Devuelve una lista con todos los autores almacenados en la base de datos")
    public List<Author> findAll() {
        return repository.findAll();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Obtener un autor por ID", description = "Devuelve un autor especifico segun el ID proporcionado")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "200", description = "Autor encontrado"),
                    @APIResponse(responseCode = "404", description = "Autor no encontrado")
            }
    )
    public Response findById(@PathParam("id") Integer id) {
        var book = repository.findById(id);
        if (book != null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book).build();
    }

    @POST
    @Operation(summary = "Agregar un autor", description = "Agrega un nuevo autor a la base de datos")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "201", description = "Libro creado exitosamente")
            }
    )
    public Response create(Author author) {
        repository.save(author);
        return Response.status(Response.Status.CREATED.getStatusCode(), "author created").build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar un autor", description = "Actualiza la información de un autor existente en la base de datos")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "204", description = "Autor actualizado exitosamente"),
                    @APIResponse(responseCode = "404", description = "Autor no encontrado")
            }
    )
    public Response update(@PathParam("id") Integer id, Author authorObj) {
        authorObj.setId(id);
        repository.save(authorObj);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar un autor", description = "Elimina un autor de la base de datos")
    @APIResponses(
            value = {
                    @APIResponse(responseCode = "204", description = "Autor eliminado exitosamente"),
                    @APIResponse(responseCode = "404", description = "Autor no encontrado")
            }
    )
    public Response delete(@PathParam("id") Integer id) {
        repository.delete(id);

        return Response.ok().build();
    }
}
