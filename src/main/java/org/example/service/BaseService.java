package org.example.service;

import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public interface BaseService<T> {
    Map<Long,T>  readAll();

    T readById(Long id);

    T create(T createRequest);

    T update(T updateRequest);

    boolean deleteById(Long id);


}
