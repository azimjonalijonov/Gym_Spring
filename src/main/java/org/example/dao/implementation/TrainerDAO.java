package org.example.dao.implementation;


import org.example.dao.BaseDAO;
import org.example.domain.Trainer;
import org.example.storage.InMemoryStorage;
import org.example.util.exception.TraineeNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainerDAO implements BaseDAO<Trainer> {


    @Override
    public Map<Long, Trainer> readAll() {
        return InMemoryStorage.getTrainerStorage();
    }

    @Override
    public Trainer readById(Long id) {
        Map<Long, Trainer> traineeMap = InMemoryStorage.getTrainerStorage();
        return traineeMap.get(id);
    }

    @Override
    public Trainer create(Trainer entity) {
        Map<Long, Trainer> traineeMap = InMemoryStorage.getTrainerStorage();
        Long id =getLastKey(traineeMap);
        id++;
        entity.setId(id);
        return traineeMap.put(id,entity);
    }


    @Override
    public Trainer update(Trainer entity) {
        Long id = entity.getId();
        Map<Long, Trainer> traineeMap =InMemoryStorage.getTrainerStorage();
        traineeMap.put(id, entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        Map<Long, Trainer> traineeMap = InMemoryStorage.getTrainerStorage();
        if (existById(id)) {
            traineeMap.remove(id);
            return true;
        }
        throw new TraineeNotFoundException(id);
    }

    @Override
    public boolean existById(Long id) {
        Map<Long, Trainer> traineeMap = InMemoryStorage.getTrainerStorage();
        return traineeMap.containsKey(id);
    }
    private static <K, V> K getLastKey(Map<K, V> map) {
        K lastKey = null;

        for (K key : map.keySet()) {
            lastKey = key;
        }

        return lastKey;
    }
}
