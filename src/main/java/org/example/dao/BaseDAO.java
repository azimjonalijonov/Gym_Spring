package org.example.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BaseDAO<T> {
    Map<Long,T> readAll();

    T readById(Long id);

    T create(T entity);

    T update(T entity);

    boolean deleteById(Long id);

    boolean existById(Long id);
}
