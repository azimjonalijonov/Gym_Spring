package org.example.user;

import org.example.util.exception.ValidatorException;
import org.example.util.validation.impl.UserErrorValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDAO userDAOMock;

    @Mock
    private UserErrorValidator userErrorValidatorMock;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadAll() {
         Map<Long, User> userMap = new HashMap<>();
        when(userDAOMock.readAll()).thenReturn(userMap);

         Map<Long, User> result = userService.readAll();

         verify(userDAOMock, times(1)).readAll();
        assertEquals(userMap, result);
    }

    @Test
    void testReadById() {
         Long userId = 1L;
        User user = new User();
        when(userDAOMock.readById(userId)).thenReturn(user);

         User result = userService.readById(userId);

         verify(userDAOMock, times(1)).readById(userId);
        assertEquals(user, result);
    }

    @Test
    void testCreate_ValidParams() {
         User user = new User();
        when(userErrorValidatorMock.isValidParamsForCreate(user)).thenReturn(true);

         User result = userService.create(user);

         verify(userErrorValidatorMock, times(1)).isValidParamsForCreate(user);
        verify(userDAOMock, times(1)).create(user);
        assertEquals(user, result);
    }

    @Test
    void testCreate_InvalidParams() {
         User user = new User();
        when(userErrorValidatorMock.isValidParamsForCreate(user)).thenReturn(false);

         assertThrows(ValidatorException.class, () -> userService.create(user));

         verify(userErrorValidatorMock, times(1)).isValidParamsForCreate(user);
        verifyNoInteractions(userDAOMock);
    }

 }
