package com.lms.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.models.User;
import com.lms.dao.repository.UserRepository;

/**
 * Created by bhushan on 9/4/17.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Transactional
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Transactional
    @Override
    public void deleteUser(Long id) {

    }

    @Transactional
    @Override
    public void updateUser(User user) {

    }

    @Transactional(readOnly = true)
    @Override
    public User loadByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public User findByUuid(String uuid) {
        return userRepository.findByUuid(uuid);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByToken(String uuid) {
        return userRepository.findByToken(uuid);
    }
}
