package org.example.config;

import org.example.Facade;
import org.example.dao.implementation.*;
import org.example.domain.*;
import org.example.service.implementation.*;
import org.example.util.DataSource;
import org.example.util.validation.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;

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
