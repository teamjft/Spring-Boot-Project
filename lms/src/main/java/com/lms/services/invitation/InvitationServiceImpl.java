package com.lms.services.invitation;

import static com.lms.utils.Constant.PAGE_SIZE;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.dao.repository.InvitationRepository;
import com.lms.models.Invitation;
import com.lms.services.notification.NotificationBuilder;
import com.lms.utils.enums.NotificationServiceType;
import com.lms.utils.enums.NotificationType;
import com.lms.utils.factory.NotificationFactory;
import com.lms.utils.notification.EmailNotification;
import com.lms.utils.notification.Notification;
import com.lms.utils.notification.mapper.EmailMapMapper;
import com.lms.utils.notification.mapper.InviteUserMapMapper;
import com.lms.utils.notification.parameterprovider.InviteUserParameter;

/**
 * Created by bhushan on 25/4/17.
 */
@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private NotificationBuilder notificationBuilder;
    @Autowired
    private NotificationFactory notificationFactory;

    @Override
    @Transactional(readOnly = true)
    public Invitation get(Long id) {
        return invitationRepository.getOne(id);
    }

    @Override
    @Transactional
    public Invitation create(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        invitationRepository.delete(id);
    }

    @Override
    @Transactional
    public void update(Invitation invitation) {
        invitationRepository.save(invitation);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Invitation> getPageRequest(Integer pageNumber) {
        PageRequest request =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        return invitationRepository.findAll(request);
    }

    @Override
    @Transactional(readOnly = true)
    public Invitation findByUuid(String uuid) {
        return invitationRepository.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void inviteUser(Invitation invitation, String invitationUrl) {
        invitation = create(invitation);
        InviteUserParameter inviteUserParameter = new InviteUserParameter(invitation.getInvitedBy().getFirstName(), invitation.getInvitedBy().getLastName(), invitation.getInviteLibrary().getName(),invitationUrl);
        EmailMapMapper emailMapMapper = new InviteUserMapMapper(inviteUserParameter);
        Pair<String, String> pair = notificationBuilder.getNotificationContentAndSubject(NotificationType.INVITEUSER, emailMapMapper);
        Notification notification = EmailNotification.builder()
                .subject(pair.getLeft())
                .content(pair.getRight())
                .to(invitation.getEmail()).build();

        notificationFactory.getSendContentService(NotificationServiceType.EMAIL). sendNotification(notification);
    }

    @Override
    public Invitation findByTokenAndNotDeleted(String token) {
        return invitationRepository.findByTokenAndDeletedFalse(token);
    }
}
