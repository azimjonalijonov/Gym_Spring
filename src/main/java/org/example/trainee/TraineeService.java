package org.example.trainee;

import org.example.interfaces.BaseService;
import org.example.util.exception.ValidatorException;
import org.example.util.validation.impl.TraineeErrorValidator;
import org.springframework.stereotype.Service;

import java.util.Map;

;

@Service
public class TraineeService implements BaseService<Trainee> {
    private final TraineeDAO traineeDAO;
    private final TraineeErrorValidator traineeErrorValidator;

    public TraineeService(TraineeDAO traineeDAO, TraineeErrorValidator traineeErrorValidator) {
        this.traineeDAO = traineeDAO;
        this.traineeErrorValidator = traineeErrorValidator;
    }


    @Override
    public Map<Long,Trainee> readAll() {
        Map<Long,Trainee> traineeMap = traineeDAO.readAll();
        if (traineeMap.isEmpty()) {
            System.out.println("The list of the trainee is empty");
        }
        return traineeMap;
    }

    @Override
    public Trainee readById(Long id) {
        return traineeDAO.readById(id);
    }

    @Override
    public Trainee create(Trainee createRequest) {
        if (traineeErrorValidator.isValidParamsForCreate(createRequest)) {
            traineeDAO.create(createRequest);
            return createRequest;
        }
        throw new ValidatorException("Something wrong with provided entity");
    }

    @Override
    public Trainee update(Trainee updateRequest) {
        if (traineeErrorValidator.isValidParamsForUpdate(updateRequest)) {
            traineeDAO.update(updateRequest);
            return updateRequest;
        }
        throw new ValidatorException("Something wrong with provided entity");
    }

    @Override
    public boolean deleteById(Long id) {
        return traineeDAO.deleteById(id);
    }
}
