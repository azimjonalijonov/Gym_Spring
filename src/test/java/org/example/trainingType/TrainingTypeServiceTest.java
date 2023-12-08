package org.example.trainingType;

import org.example.util.exception.ValidatorException;
import org.example.util.validation.impl.TrainingTypeErrorValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingTypeServiceTest {

    @Mock
    private TrainingTypeDAO trainingTypeDAOMock;

    @Mock
    private TrainingTypeErrorValidator trainingTypeErrorValidatorMock;

    @InjectMocks
    private TrainingTypeService trainingTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadAll() {
         Map<Long, TrainingType> trainingTypeMap = new HashMap<>();
        when(trainingTypeDAOMock.readAll()).thenReturn(trainingTypeMap);

         Map<Long, TrainingType> result = trainingTypeService.readAll();

         verify(trainingTypeDAOMock, times(1)).readAll();
        assertEquals(trainingTypeMap, result);
    }

    @Test
    void testReadById() {
         Long trainingTypeId = 1L;
        TrainingType trainingType = new TrainingType(trainingTypeId, "Training Type Name");
        when(trainingTypeDAOMock.readById(trainingTypeId)).thenReturn(trainingType);

         TrainingType result = trainingTypeService.readById(trainingTypeId);

         verify(trainingTypeDAOMock, times(1)).readById(trainingTypeId);
        assertEquals(trainingType, result);
    }

    @Test
    void testCreate_ValidParams() {
         TrainingType trainingType = new TrainingType(1L, "Training Type Name");
        when(trainingTypeErrorValidatorMock.isValidParamsForCreate(trainingType)).thenReturn(true);

         when(trainingTypeDAOMock.create(trainingType)).thenReturn(trainingType);

         TrainingType result = trainingTypeService.create(trainingType);

         verify(trainingTypeErrorValidatorMock, times(1)).isValidParamsForCreate(trainingType);
        verify(trainingTypeDAOMock, times(1)).create(trainingType);
        assertEquals(trainingType, result);
    }

    @Test
    void testCreate_InvalidParams() {
         TrainingType trainingType = new TrainingType(1L, "Training Type Name");
        when(trainingTypeErrorValidatorMock.isValidParamsForCreate(trainingType)).thenReturn(false);

         assertThrows(ValidatorException.class, () -> trainingTypeService.create(trainingType));

         verify(trainingTypeErrorValidatorMock, times(1)).isValidParamsForCreate(trainingType);
        verifyNoInteractions(trainingTypeDAOMock);
    }

 }
