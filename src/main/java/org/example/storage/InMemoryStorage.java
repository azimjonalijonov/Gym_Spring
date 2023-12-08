package org.example.storage;

import org.example.trainee.Trainee;
import org.example.trainer.Trainer;
import org.example.training.Training;
import org.example.trainingType.TrainingType;
import org.example.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class InMemoryStorage {
    private static Map<Long, Trainee> traineeStorage =new HashMap<>();
    private static Map<Long, Trainer> trainerStorage=new HashMap<>();
    private static Map<Long, Training> trainingStorage=new HashMap<>();
    private static Map<Long, TrainingType> TrainingTypeStorage=new HashMap<>();

    public InMemoryStorage() {
    }

    private static Map<Long, User> UserStorage=new HashMap<>();

    public static Map<Long, Trainee> getTraineeStorage() {
        return traineeStorage;
    }

    public static void setTraineeStorage(Map<Long, Trainee> traineeStorage) {
        InMemoryStorage.traineeStorage = traineeStorage;
    }

    public static Map<Long, Trainer> getTrainerStorage() {
        return trainerStorage;
    }

    public static void setTrainerStorage(Map<Long, Trainer> trainerStorage) {
        InMemoryStorage.trainerStorage = trainerStorage;
    }

    public static Map<Long, Training> getTrainingStorage() {
        return trainingStorage;
    }

    public static void setTrainingStorage(Map<Long, Training> trainingStorage) {
        InMemoryStorage.trainingStorage = trainingStorage;
    }

    public static Map<Long, TrainingType> getTrainingTypeStorage() {
        return TrainingTypeStorage;
    }

    public static void setTrainingTypeStorage(Map<Long, TrainingType> trainingTypeStorage) {
        TrainingTypeStorage = trainingTypeStorage;
    }

    public static Map<Long, User> getUserStorage() {
        return UserStorage;
    }

    public static void setUserStorage(Map<Long, User> userStorage) {
        UserStorage = userStorage;
    }




}
