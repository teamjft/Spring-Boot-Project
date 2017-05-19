package com.lms.services.user;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.models.User;
import com.lms.dao.repository.UserRepository;
import com.lms.services.notification.NotificationBuilder;
import com.lms.utils.enums.NotificationServiceType;
import com.lms.utils.enums.NotificationType;
import com.lms.config.factory.NotificationFactory;
import com.lms.utils.notification.EmailNotification;
import com.lms.utils.notification.Notification;
import com.lms.utils.notification.mapper.EmailMapMapper;
import com.lms.utils.notification.mapper.ForgetMapPassword;
import com.lms.utils.notification.parameterprovider.ForgetPasswordParameter;

/**
 * Created by bhushan on 9/4/17.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationBuilder notificationBuilder;
    @Autowired
    private NotificationFactory notificationFactory;

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
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
        userRepository.delete(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
           userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User loadByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return userRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUuid(String uuid) {
        return userRepository.findByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByToken(String uuid) {
        return userRepository.findByToken(uuid);
    }

    @Override
    @Transactional
    public void requestForgetPassword(String forgetPasswordUrl, User user) {
        String token = UUID.randomUUID().toString();
        forgetPasswordUrl = String.format("%s/%s", forgetPasswordUrl, token);
        ForgetPasswordParameter passwordParameter = ForgetPasswordParameter.builder()
                .forgetPasswordUrl(forgetPasswordUrl)
                .userName(user.getEmail())
                .build();

        EmailMapMapper emailMapMapper = new ForgetMapPassword(passwordParameter);
        Pair<String, String> pair = notificationBuilder.getNotificationContentAndSubject(NotificationType.FORGETPASSWORD, emailMapMapper);
        user.setToken(token);
        updateUser(user);
        Notification notification = EmailNotification.builder()
                .subject(pair.getLeft())
                .content(pair.getRight())
                .to(user.getEmail()).build();

        notificationFactory.getSendContentService(NotificationServiceType.EMAIL). sendNotification(notification);
    }

    @Override
    public User findByEmailOrUsername(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username);
    }
}
