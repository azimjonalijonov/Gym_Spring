package org.example.service.implementation;


import org.example.dao.implementation.TrainingDAO;
import org.example.domain.Training;
import org.example.service.BaseService;
import org.example.util.validation.impl.TrainingErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class TrainingService implements BaseService<Training> {
    private  final TrainingDAO trainingDAO;
     private  final TrainingErrorValidator trainingErrorValidator;
@Autowired
    public TrainingService(TrainingDAO trainingDAO, TrainingErrorValidator trainingErrorValidator) {
        this.trainingDAO = trainingDAO;
        this.trainingErrorValidator = trainingErrorValidator;
    }


    @Override
    public Map<Long, Training> readAll() {
        return trainingDAO.readAll();
    }

    @Override
    public Training readById(Long id) {
        return trainingDAO.readById(id);
    }

    @Override
    public Training create(Training createRequest) {
        if (trainingErrorValidator.isValidParamsForCreate(createRequest)) {
            return trainingDAO.create(createRequest);
        }
        throw new RuntimeException("Some thing is wrong with provided entity");
    }

    @Override
    public Training update(Training updateRequest) {
        if (trainingErrorValidator.isValidParamsForUpdate(updateRequest)) {
            return trainingDAO.update(updateRequest);
        }
        throw new RuntimeException("Some thing is wrong with provided entity");
    }

    @Override
    public boolean deleteById(Long id) {
        return trainingDAO.deleteById(id);
    }
}
