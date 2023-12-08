package org.example.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.storage.InMemoryStorage;
import org.example.trainee.Trainee;
import org.example.trainer.Trainer;
import org.example.training.Training;
import org.example.trainingType.TrainingType;
import org.example.user.User;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Component
public class DataSource implements BeanPostProcessor {

//    @Value("${user.file.path}")
    private String userFilePath="src/main/resources/user.txt";

//    @Value("${trainee.file.path}")
    private String traineeFilePath="src/main/resources/trainee.txt";

//    @Value("${trainer.file.path}")
    private String trainerFilePath="src/main/resources/trainer.txt";

//    @Value("${training.file.path}")
    private String trainingFilePath="src/main/resources/training.txt";

//    @Value("${trainingType.file.path}")
    private String trainingTypeFilePath="src/main/resources/trainingType.txt";

    private static Logger logger = LogManager.getLogger(DataSource.class);


    @PostConstruct


   public void initUser() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath));
            String temp;
            Long key = 1L;
            while ((temp = bufferedReader.readLine()) != null) {
                String[] entity = temp.split(":");
                Long id = Long.parseLong(entity[0]);
                String firstName = entity[1];
                String lastName = entity[2];
                String username = entity[3];
                String password = entity[4];
                boolean isActive = Boolean.parseBoolean(entity[5]);
                User user = new User(id, firstName, lastName, username, password, isActive);
                InMemoryStorage.getUserStorage().put(key,user);
                logger.info("user number "+key+" is initialized");

                key++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostConstruct

    public  void initTrainee() {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(traineeFilePath), StandardCharsets.UTF_8);
            String temp;
            Long key = 1L;
            while ((temp = bufferedReader.readLine()) != null) {
                String[] trainee = temp.split(":");
                Long id = Long.parseLong(trainee[0]);
                LocalDate dateOfBirth = LocalDate.parse(trainee[1]);
                String address = trainee[2];
                Long userId = Long.parseLong(trainee[3]);
                Trainee entity = new Trainee(id, dateOfBirth, address, userId);
                InMemoryStorage.getTraineeStorage().put(key, entity);
                key++;
            }
            logger.info("trainees are initiliazed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostConstruct

    public  void initTrainer() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(trainerFilePath));
            String temp;
            Long key = 1L;
            while ((temp = bufferedReader.readLine()) != null) {
                String[] trainer = temp.split(":");
                Long id = Long.parseLong(trainer[0]);
                Long specializationId = Long.parseLong(trainer[1]);
                Long userId = Long.parseLong(trainer[2]);
                Trainer entity = new Trainer(id, specializationId, userId);
                InMemoryStorage.getTrainerStorage().put(key, entity);
                key++;

            }
            logger.info("trainers are initilized");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostConstruct

    public void initTrainingType() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(trainingTypeFilePath));
            String temp;
            Long key = 1L;
            while ((temp = bufferedReader.readLine()) != null) {
                String[] type = temp.split(":");
                Long id = Long.parseLong(type[0]);
                String name = type[1];
                TrainingType trainingType = new TrainingType(id, name);
                InMemoryStorage.getTrainingTypeStorage().put(key, trainingType);
                key++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostConstruct

    public void initTraining() {
        try {       BufferedReader bufferedReader = new BufferedReader(new FileReader(trainingFilePath));
            String temp;
            Long key = 1L;
            while ((temp = bufferedReader.readLine()) != null) {
                String[] training = temp.split(":");
                Long id = Long.parseLong(training[0]);
                Long traineeId = Long.parseLong(training[1]);
                Long trainerId = Long.parseLong(training[2]);
                String trainingName = training[3];
                Long trainingType = Long.parseLong(training[4]);
                LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 13, 17, 00);
                Number duration = (Number) Integer.parseInt(training[5]);
                Training entity = new Training(id, traineeId, trainerId, trainingName, trainingType, localDateTime, duration);
                InMemoryStorage.getTrainingStorage().put(key, entity);
                key++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
