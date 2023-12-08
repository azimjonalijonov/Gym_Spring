package org.example.config;

import org.example.Facade;
import org.example.storage.InMemoryStorage;
import org.example.trainee.Trainee;
import org.example.trainee.TraineeDAO;
import org.example.trainee.TraineeService;
import org.example.trainer.Trainer;
import org.example.trainer.TrainerDAO;
import org.example.trainer.TrainerService;
import org.example.training.Training;
import org.example.training.TrainingDAO;
import org.example.training.TrainingService;
import org.example.trainingType.TrainingType;
import org.example.trainingType.TrainingTypeDAO;
import org.example.trainingType.TrainingTypeService;
import org.example.user.User;
import org.example.user.UserDAO;
import org.example.user.UserService;
import org.example.util.DataSource;
import org.example.util.validation.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AplicationConfiguration {
    @Bean
    public DataSource get(){
        return new DataSource();
    }
    @Bean
    public User getUser(){
        return new User();
    }
    @Bean
    public Training getTraining(){
        return new Training();
    }
    @Bean
    public Trainer getTrainer(){
        return new Trainer();
    }
    @Bean
    public Trainee gettrainee(){
        return new Trainee();
    }
    @Bean
    public TraineeDAO traineeDAO(){
        return new TraineeDAO();
    }
    @Bean
    public TraineeErrorValidator traineeErrorValidator(){
        return new TraineeErrorValidator();
    }
    @Bean
   public TrainingType getTrainingType(){
        return new TrainingType()
;    }
    @Bean
    public UserDAO userDAO(){
        return new UserDAO();

    }
    @Bean
    public InMemoryStorage inMemoryStorage(){
        return new InMemoryStorage();
    }

    @Bean
    public UserErrorValidator userErrorValidator(){
        return new UserErrorValidator();

    }
    @Bean
    public TrainerDAO trainerDAO(){
        return new TrainerDAO();
    }
    @Bean
    public TrainerErrorValidator trainerErrorValidator(){
        return new TrainerErrorValidator() ;
    }
    @Bean
    public TrainingDAO trainingDAO(){
        return new TrainingDAO();
    }
    @Bean
    public TrainingErrorValidator trainingErrorValidator(){
        return new TrainingErrorValidator() ;
    }

    @Bean
    public TrainingTypeDAO trainingTypeDAO(){
        return new TrainingTypeDAO();
    }
    @Bean
    public TrainingTypeErrorValidator trainingTypeErrorValidator(){
        return new TrainingTypeErrorValidator();
    }

    @Bean
    public UserService userService() {
        return new UserService(userDAO(), userErrorValidator());
    }

    @Bean
    public TraineeService traineeService() {
        return new TraineeService(traineeDAO(), traineeErrorValidator());
    }

    @Bean
    public TrainerService trainerService() {
        return new TrainerService(trainerDAO(), trainerErrorValidator());
    }

    @Bean
    public TrainingService trainingService() {
        return new TrainingService(trainingDAO(), trainingErrorValidator());
    }

    @Bean
    public TrainingTypeService trainingTypeService() {
        return new TrainingTypeService(trainingTypeDAO(), trainingTypeErrorValidator());
    }
    @Bean
    public Facade facade(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingService trainingService, TrainingTypeService trainingTypeService) {
        return new Facade(userService, traineeService, trainerService, trainingTypeService, trainingService);
    }


}
