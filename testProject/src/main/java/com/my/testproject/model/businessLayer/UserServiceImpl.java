package com.my.testproject.model.businessLayer;

import com.my.testproject.model.businessLayer.exception.UserNotFoundException;
import com.my.testproject.model.persistenceLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = null;
    }
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        //It would be better to handle exceptions in controller and log details about what happened, sending back corresponding response
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("by id : " + id));
    }
}
