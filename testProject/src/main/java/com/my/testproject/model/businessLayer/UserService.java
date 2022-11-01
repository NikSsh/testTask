package com.my.testproject.model.businessLayer;

import org.springframework.stereotype.Service;

public interface UserService {
    User getUserById(Long id);
}
