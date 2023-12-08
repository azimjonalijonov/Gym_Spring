package org.example.trainer;

import org.example.trainer.Trainer;
import org.example.trainer.TrainerDAO;
import org.example.util.exception.ValidatorException;
import org.example.util.validation.impl.TrainerErrorValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerServiceTest {

    @Mock
    private TrainerDAO trainerDAO;

    @Mock
    private TrainerErrorValidator trainerErrorValidator;

    @InjectMocks
    private TrainerService trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void readAllShouldReturnEmptyMap() {
         when(trainerDAO.readAll()).thenReturn(Collections.emptyMap());

         Map<Long, Trainer> result = trainerService.readAll();

         assertTrue(result.isEmpty());
    }

    @Test
    void readByIdShouldReturnTrainer() {
         Long trainerId = 1L;
        Trainer expectedTrainer = new Trainer();
        when(trainerDAO.readById(trainerId)).thenReturn(expectedTrainer);

        //
        Trainer result = trainerService.readById(trainerId);

         assertEquals(expectedTrainer, result);
    }

    @Test
    void createShouldCreateTrainer() {
         Trainer trainerToCreate = new Trainer();
        when(trainerErrorValidator.isValidParamsForCreate(trainerToCreate)).thenReturn(true);

        Trainer result = trainerService.create(trainerToCreate);

         verify(trainerDAO, times(1)).create(trainerToCreate);
    }

    @Test
    void createShouldThrowRuntimeException() {
         Trainer trainerToCreate = new Trainer();
        when(trainerErrorValidator.isValidParamsForCreate(trainerToCreate)).thenReturn(false);

         assertThrows(RuntimeException.class, () -> trainerService.create(trainerToCreate));
        verify(trainerDAO, never()).create(trainerToCreate);
    }

    @Test
    void updateShouldUpdateTrainer() {
         Trainer trainerToUpdate = new Trainer();
        when(trainerErrorValidator.isValidParamsForUpdate(trainerToUpdate)).thenReturn(true);


        Trainer result = trainerService.update(trainerToUpdate);


         verify(trainerDAO, times(1)).update(trainerToUpdate);
    }

    @Test
    void updateShouldThrowValidatorException() {

        Trainer trainerToUpdate = new Trainer();
        when(trainerErrorValidator.isValidParamsForUpdate(trainerToUpdate)).thenReturn(false);


        assertThrows(ValidatorException.class, () -> trainerService.update(trainerToUpdate));
        verify(trainerDAO, never()).update(trainerToUpdate);
    }

    @Test
    void deleteByIdShouldReturnTrue() {

        Long trainerId = 1L;


        boolean result = trainerService.deleteById(trainerId);

         verify(trainerDAO, times(1)).deleteById(trainerId);
    }
}
