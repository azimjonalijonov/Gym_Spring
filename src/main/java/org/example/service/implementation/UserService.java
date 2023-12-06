package org.example.service.implementation;


import org.example.dao.implementation.UserDAO;
import org.example.domain.User;
import org.example.service.BaseService;
import org.example.util.exception.ValidatorException;
import org.example.util.validation.impl.UserErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
 public class UserService implements BaseService<User> {
    private  final UserDAO userDAO;
    private  final UserErrorValidator userErrorValidator;
@Autowired
    public UserService(UserDAO userDAO, UserErrorValidator userErrorValidator) {
        this.userDAO = userDAO;
        this.userErrorValidator = userErrorValidator;
    }


    @Override
    public Map<Long,User> readAll() {
        Map<Long,User>  userList = userDAO.readAll();
        return userList;
    }

    @Override
    public User readById(Long id) {
        return userDAO.readById(id);
    }

    @Override
    public User create(User createRequest) {
        if (userErrorValidator.isValidParamsForCreate(createRequest)) {
            userDAO.create(createRequest);
            return createRequest;
        } else {
            throw new ValidatorException("Something wrong with parameters");
        }
    }

    @Override
    public User update(User updateRequest) {
        if (userErrorValidator.isValidParamsForUpdate(updateRequest)) {
            userDAO.update(updateRequest);
            return updateRequest;
        }
        throw new ValidatorException("Something wrong with parameters");
    }

    @Override
    public boolean deleteById(Long id) {
        return userDAO.deleteById(id);
    }
}
