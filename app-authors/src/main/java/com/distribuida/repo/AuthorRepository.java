package com.distribuida.repo;

import com.distribuida.db.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class AuthorRepository {
    @Inject
    private EntityManager entityManager;

    public Author findById(Integer id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    public Author save(Author obj) {
        if (obj.getId() == null) {
            entityManager.persist(obj);
        } else {
            entityManager.merge(obj);
        }
        return obj;
    }

    public void delete(Integer id) {
        Author author = findById(id);
        if (author != null) {
            entityManager.remove(author);
        }
    }
}
