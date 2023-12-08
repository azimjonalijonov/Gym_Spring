package org.example.training;


import org.example.interfaces.BaseDAO;
import org.example.storage.InMemoryStorage;
import org.example.util.exception.TraineeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainingDAO implements BaseDAO<Training> {
    private  InMemoryStorage inMemoryStorage;
    @Autowired
    public  void  setInMemoryStorage(InMemoryStorage inMemoryStorage){
        this.inMemoryStorage=inMemoryStorage;
    }

    @Override
    public Map<Long, Training> readAll() {
        return inMemoryStorage.getTrainingStorage();
    }

    @Override
    public Training readById(Long id) {
        Map<Long, Training> traineeMap = inMemoryStorage.getTrainingStorage();
        return traineeMap.get(id);
    }

    @Override
    public Training create(Training entity) {
        Map<Long, Training> traineeMap = inMemoryStorage.getTrainingStorage();
        Long id =getLastKey(traineeMap);
        id++;
        entity.setId(id);
        return traineeMap.put(id,entity);
    }


    @Override
    public Training update(Training entity) {
        Long id = entity.getId();
        Map<Long, Training> traineeMap =inMemoryStorage.getTrainingStorage();
        traineeMap.put(id, entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        Map<Long, Training> traineeMap = inMemoryStorage.getTrainingStorage();
        if (existById(id)) {
            traineeMap.remove(id);
            return true;
        }
        throw new TraineeNotFoundException(id);
    }

    @Override
    public boolean existById(Long id) {
        Map<Long, Training> traineeMap = inMemoryStorage.getTrainingStorage();
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
