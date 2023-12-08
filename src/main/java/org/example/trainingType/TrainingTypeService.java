package org.example.trainingType;


import org.example.interfaces.BaseService;
import org.example.util.exception.ValidatorException;
import org.example.util.validation.impl.TrainingTypeErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrainingTypeService implements BaseService<TrainingType> {
   private  final TrainingTypeDAO trainingTypeDAO;

   private  final TrainingTypeErrorValidator trainingTypeErrorValidator;
@Autowired
    public TrainingTypeService(TrainingTypeDAO trainingTypeDAO, TrainingTypeErrorValidator trainingTypeErrorValidator) {
        this.trainingTypeDAO = trainingTypeDAO;
        this.trainingTypeErrorValidator = trainingTypeErrorValidator;
    }


    @Override
    public Map<Long,TrainingType>readAll() {
        return trainingTypeDAO.readAll();
    }

    @Override
    public TrainingType readById(Long id) {
        return trainingTypeDAO.readById(id);
    }

    @Override
    public TrainingType create(TrainingType createRequest) {
        if (trainingTypeErrorValidator.isValidParamsForCreate(createRequest)) {
            return trainingTypeDAO.create(createRequest);
        }
        throw new ValidatorException("Something is wrong");
    }

    @Override
    public TrainingType update(TrainingType updateRequest) {
        if (trainingTypeErrorValidator.isValidParamsForUpdate(updateRequest)) {
            return trainingTypeDAO.update(updateRequest);
        }
        throw new ValidatorException("Something is wrong");
    }

    @Override
    public boolean deleteById(Long id) {
        return trainingTypeDAO.deleteById(id);
    }
}
