package org.example.trainee;


import org.example.util.exception.ValidatorException;
import org.example.util.validation.impl.TraineeErrorValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraineeServiceTest {

    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private TraineeErrorValidator traineeErrorValidator;

    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void readAllShouldReturnEmptyMap() {
         when(traineeDAO.readAll()).thenReturn(Collections.emptyMap());

         Map<Long, Trainee> result = traineeService.readAll();

         assertTrue(result.isEmpty());
    }

    @Test
    void readByIdShouldReturnTrainee() {
        Long traineeId = 1L;
        Trainee expectedTrainee = new Trainee(); // Create a Trainee object with appropriate data
        when(traineeDAO.readById(traineeId)).thenReturn(expectedTrainee);

        Trainee result = traineeService.readById(traineeId);

        assertEquals(expectedTrainee, result);
    }

    @Test
    void createShouldCreateTrainee() {
        Trainee traineeToCreate = new Trainee(); // Create a Trainee object with appropriate data
        when(traineeErrorValidator.isValidParamsForCreate(traineeToCreate)).thenReturn(true);


        Trainee result = traineeService.create(traineeToCreate);


        assertEquals(traineeToCreate, result);
        verify(traineeDAO, times(1)).create(traineeToCreate);
    }

    @Test
    void createShouldThrowValidatorException() {
        Trainee traineeToCreate = new Trainee(); // Create a Trainee object with appropriate data
        when(traineeErrorValidator.isValidParamsForCreate(traineeToCreate)).thenReturn(false);

        assertThrows(ValidatorException.class, () -> traineeService.create(traineeToCreate));
        verify(traineeDAO, never()).create(traineeToCreate);
    }

    @Test
    void updateShouldUpdateTrainee() {
        Trainee traineeToUpdate = new Trainee(); // Create a Trainee object with appropriate data
        when(traineeErrorValidator.isValidParamsForUpdate(traineeToUpdate)).thenReturn(true);

        Trainee result = traineeService.update(traineeToUpdate);

        assertEquals(traineeToUpdate, result);
        verify(traineeDAO, times(1)).update(traineeToUpdate);
    }

    @Test
    void updateShouldThrowValidatorException() {
        Trainee traineeToUpdate = new Trainee(); // Create a Trainee object with appropriate data
        when(traineeErrorValidator.isValidParamsForUpdate(traineeToUpdate)).thenReturn(false);

        assertThrows(ValidatorException.class, () -> traineeService.update(traineeToUpdate));
        verify(traineeDAO, never()).update(traineeToUpdate);
    }

    @Test
    void deleteByIdShouldReturnTrue() {
         Long traineeId = 1L;


         traineeService.deleteById(traineeId);

         verify(traineeDAO, times(1)).deleteById(traineeId);
    }
}
