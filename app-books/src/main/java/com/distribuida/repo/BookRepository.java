package com.distribuida.repo;

import com.distribuida.db.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class BookRepository {
    @Inject
    private EntityManager entityManager;

    public Book findById(Integer id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public Book save(Book obj) {
        if (obj.getId() == null) {
            entityManager.persist(obj);
        } else {
            entityManager.merge(obj);
        }
        return obj;
    }

    public void delete(Integer id) {
        Book author = findById(id);
        if (author != null) {
            entityManager.remove(author);
        }
    }
}
