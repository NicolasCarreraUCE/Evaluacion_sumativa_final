package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.repo.AuthorRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AuthorRest {
    @Inject
    private AuthorRepository repository;

    @GET
    public List<Author> findAll() {
        return repository.findAll();
    }

}
