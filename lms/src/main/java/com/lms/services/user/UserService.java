package com.lms.services.user;

import java.util.List;

import com.lms.models.User;

/**
 * Created by bhushan on 9/4/17.
 */
 public interface UserService {
    User getUserById(Long id);
    List<User> getAllUser();
    User createUser(User user);
    void deleteUser(Long id);
    void updateUser(User user);
    User loadByUsername(String username);
    Long count();
    User findByUuid(String uuid);
    User findByToken(String uuid);
    User findByEmail(String email);
    void requestForgetPassword(String forgetPasswordUrl, User user);
}
