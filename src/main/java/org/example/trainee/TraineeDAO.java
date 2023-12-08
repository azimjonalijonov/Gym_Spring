package org.example.trainee;

import org.example.interfaces.BaseDAO;
import org.example.storage.InMemoryStorage;
import org.example.util.exception.TraineeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

;


@Repository
public class TraineeDAO implements BaseDAO<Trainee> {

    private  InMemoryStorage inMemoryStorage;
    @Autowired
    public  void  setInMemoryStorage(InMemoryStorage inMemoryStorage){
        this.inMemoryStorage=inMemoryStorage;
    }


    @Override
    public Map<Long,Trainee> readAll() {
        return inMemoryStorage.getTraineeStorage();
    }

    @Override
    public Trainee readById(Long id) {
        Map<Long, Trainee> traineeMap = inMemoryStorage.getTraineeStorage();
        return traineeMap.get(id);
    }

    @Override
    public Trainee create(Trainee entity) {
        Map<Long, Trainee> traineeMap = inMemoryStorage.getTraineeStorage();
        Long id =getLastKey(traineeMap);
        id++;
        entity.setId(id);
         return traineeMap.put(id,entity);
        }


    @Override
    public Trainee update(Trainee entity) {
        Long id = entity.getId();
        Map<Long, Trainee> traineeMap =inMemoryStorage.getTraineeStorage();
        traineeMap.put(id, entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        Map<Long, Trainee> traineeMap = inMemoryStorage.getTraineeStorage();
        if (existById(id)) {
            traineeMap.remove(id);
            return true;
        }
        throw new TraineeNotFoundException(id);
    }

    @Override
    public boolean existById(Long id) {
        Map<Long, Trainee> traineeMap = inMemoryStorage.getTraineeStorage();
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
