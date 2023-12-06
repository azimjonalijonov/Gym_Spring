package org.example.service.implementation;


import org.example.dao.implementation.TrainerDAO;
import org.example.domain.Trainer;
import org.example.service.BaseService;
import org.example.util.exception.ValidatorException;
import org.example.util.validation.impl.TrainerErrorValidator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrainerService implements BaseService<Trainer> {
     private  final TrainerDAO trainerDAO;

    private  final TrainerErrorValidator trainerErrorValidator;

    public TrainerService(TrainerDAO trainerDAO, TrainerErrorValidator trainerErrorValidator) {
        this.trainerDAO = trainerDAO;
        this.trainerErrorValidator = trainerErrorValidator;
    }


    @Override
    public Map<Long,Trainer> readAll() {
        return trainerDAO.readAll();
    }

    @Override
    public Trainer readById(Long id) {
        return trainerDAO.readById(id);
    }

    @Override
    public Trainer create(Trainer createRequest) {
        if (trainerErrorValidator.isValidParamsForCreate(createRequest)) {
            return trainerDAO.create(createRequest);
        }
        throw new RuntimeException("Some thing wrong validator");
    }

    @Override
    public Trainer update(Trainer updateRequest) {
        if (trainerErrorValidator.isValidParamsForUpdate(updateRequest)) {
            return trainerDAO.update(updateRequest);
        }
        throw new ValidatorException("SOme thing wrong with provided entity");
    }

    @Override
    public boolean deleteById(Long id) {
        return trainerDAO.deleteById(id);
    }
}
