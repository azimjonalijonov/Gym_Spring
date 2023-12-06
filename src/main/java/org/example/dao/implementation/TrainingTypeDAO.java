package org.example.dao.implementation;


import org.example.dao.BaseDAO;
import org.example.domain.TrainingType;
import org.example.storage.InMemoryStorage;
import org.example.util.exception.TraineeNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainingTypeDAO implements BaseDAO<TrainingType> {
    @Override
    public Map<Long, TrainingType> readAll() {
        return InMemoryStorage.getTrainingTypeStorage();
    }

    @Override
    public TrainingType readById(Long id) {
        Map<Long, TrainingType> traineeMap = InMemoryStorage.getTrainingTypeStorage();
        return traineeMap.get(id);
    }

    @Override
    public TrainingType create(TrainingType entity) {
        Map<Long, TrainingType> traineeMap = InMemoryStorage.getTrainingTypeStorage();
        Long id =getLastKey(traineeMap);
        id++;
        entity.setId(id);
        return traineeMap.put(id,entity);
    }


    @Override
    public TrainingType update(TrainingType entity) {
        Long id = entity.getId();
        Map<Long, TrainingType> traineeMap =InMemoryStorage.getTrainingTypeStorage();
        traineeMap.put(id, entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        Map<Long, TrainingType> traineeMap = InMemoryStorage.getTrainingTypeStorage();
        if (existById(id)) {
            traineeMap.remove(id);
            return true;
        }
        throw new TraineeNotFoundException(id);
    }

    @Override
    public boolean existById(Long id) {
        Map<Long, TrainingType> traineeMap = InMemoryStorage.getTrainingTypeStorage();
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
