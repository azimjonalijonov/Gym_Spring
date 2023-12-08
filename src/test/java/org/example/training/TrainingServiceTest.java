package org.example.training;

import org.example.util.validation.impl.TrainingErrorValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingServiceTest {

    @Mock
    private TrainingDAO trainingDAOMock;

    @Mock
    private TrainingErrorValidator trainingErrorValidatorMock;

    @InjectMocks
    private TrainingService trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadAll() {
         Map<Long, Training> trainingMap = new HashMap<>();
        when(trainingDAOMock.readAll()).thenReturn(trainingMap);

         Map<Long, Training> result = trainingService.readAll();

         verify(trainingDAOMock, times(1)).readAll();
        assertEquals(trainingMap, result);
    }

    @Test
    void testReadById() {
         Long trainingId = 1L;
        Training training = new Training( );
        when(trainingDAOMock.readById(trainingId)).thenReturn(training);

         Training result = trainingService.readById(trainingId);

         verify(trainingDAOMock, times(1)).readById(trainingId);
        assertEquals(training, result);
    }

    @Test
    void testCreate_ValidParams() {
         Training training = new Training();
        when(trainingErrorValidatorMock.isValidParamsForCreate(training)).thenReturn(true);

         when(trainingDAOMock.create(training)).thenReturn(training);

         Training result = trainingService.create(training);

         verify(trainingErrorValidatorMock, times(1)).isValidParamsForCreate(training);
        verify(trainingDAOMock, times(1)).create(training);
        assertEquals(training, result);
    }

    @Test
    void testCreate_InvalidParams() {
         Training training = new Training( );
        when(trainingErrorValidatorMock.isValidParamsForCreate(training)).thenReturn(false);

         assertThrows(RuntimeException.class, () -> trainingService.create(training));

         verify(trainingErrorValidatorMock, times(1)).isValidParamsForCreate(training);
        verifyNoInteractions(trainingDAOMock);
    }

 }
