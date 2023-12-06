package org.example.dao.implementation;


import org.example.dao.BaseDAO;
import org.example.domain.Training;
import org.example.storage.InMemoryStorage;
import org.example.util.exception.TraineeNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainingDAO implements BaseDAO<Training> {

    @Override
    public Map<Long, Training> readAll() {
        return InMemoryStorage.getTrainingStorage();
    }

    @Override
    public Training readById(Long id) {
        Map<Long, Training> traineeMap = InMemoryStorage.getTrainingStorage();
        return traineeMap.get(id);
    }

    @Override
    public Training create(Training entity) {
        Map<Long, Training> traineeMap = InMemoryStorage.getTrainingStorage();
        Long id =getLastKey(traineeMap);
        id++;
        entity.setId(id);
        return traineeMap.put(id,entity);
    }


    @Override
    public Training update(Training entity) {
        Long id = entity.getId();
        Map<Long, Training> traineeMap =InMemoryStorage.getTrainingStorage();
        traineeMap.put(id, entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        Map<Long, Training> traineeMap = InMemoryStorage.getTrainingStorage();
        if (existById(id)) {
            traineeMap.remove(id);
            return true;
        }
        throw new TraineeNotFoundException(id);
    }

    @Override
    public boolean existById(Long id) {
        Map<Long, Training> traineeMap = InMemoryStorage.getTrainingStorage();
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
