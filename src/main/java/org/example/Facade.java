package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.*;
import org.example.service.implementation.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

public class Facade {
    private static Logger logger = LogManager.getLogger(Facade.class);

    private final UserService userService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingTypeService trainingTypeService;
    private final TrainingService trainingService;
public Facade(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingTypeService trainingTypeService, TrainingService trainingService) {
        this.userService = userService;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingTypeService = trainingTypeService;
        this.trainingService = trainingService;
    }

//@PostConstruct
    public void readAllUsers(){
   Map<Long, User> map = userService.readAll();
   logger.info("---------------------------------------------------------------------------------------------");
   logger.info("these are users initialized by default");

   logger.info(map.toString());
        logger.info("---------------------------------------------------------------------------------------------");

    }
public void readAllTrainees(){
    Map<Long, Trainee> map = traineeService.readAll();
    logger.info("these are trainees initialized by default");
    logger.info(map.toString());
    logger.info("---------------------------------------------------------------------------------------------");

}
public  void readALLTrainers(){
    Map<Long, Trainer> map = trainerService.readAll();
    logger.info("these are trainers initialized by default");
    logger.info(map.toString());
    logger.info("---------------------------------------------------------------------------------------------");
}
public void  readAllTrainings(){
    Map<Long, Training> map = trainingService.readAll();
    logger.info("these are trainings initialized by default");
    logger.info(map.toString());
    logger.info("---------------------------------------------------------------------------------------------");

}
public void readAllTrainingTypes(){
    Map<Long, TrainingType> map = trainingTypeService.readAll();
    logger.info("these are trainingTypes initialized by default");
    logger.info(map.toString());
    logger.info("---------------------------------------------------------------------------------------------");
}

}
