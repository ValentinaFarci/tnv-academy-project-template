package com.thenetvalue.sbTutorial1.service;

import com.thenetvalue.sbTutorial1.dao.UserDAO;
import com.thenetvalue.sbTutorial1.dao.UserRepositoryDAO;
import com.thenetvalue.sbTutorial1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepositoryDAO userDAO;

    @Autowired
    public UserService(@Qualifier("dbUserDAO") UserRepositoryDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User addUser(User user) {
        User result = userDAO.save(user);
        if (result != null) {
            return result;
        } else {
            return null;
        }
    }

    public User getUser(int id) {
        return userDAO.findById(id).get();
    }

    public Iterable<User> getUsers() {
        return userDAO.findAll();
    }

    public User putUser(int id, User user) {
        User resultUser = userDAO.findById(id).get();
        if (resultUser == null){
            return null;
        }
        resultUser.setName(user.getName());
        resultUser.setSurname(user.getSurname());
        resultUser.setUsername(user.getUsername());

        User result = userDAO.save(resultUser);
        return result;
    }

    public String deleteUser(int id) {
        User result = userDAO.findById(id).get();
        if (result == null){
            return "utente non trovato";
        }else {
            userDAO.delete(result);
            return "utente cancellato correttamente";
        }

    }

    public Iterable<User> getUsersByUsernameContains(String user){
        return this.userDAO.findByUsernameContains(user);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User result = this.userDAO.findByUsernameAndPassword(username, password);

        return result;
    }
}

