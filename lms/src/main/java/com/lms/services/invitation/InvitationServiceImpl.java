package com.lms.services.invitation;

import static com.lms.utils.Constant.PAGE_SIZE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lms.dao.repository.InvitationRepository;
import com.lms.models.Book;
import com.lms.models.Invitation;
import com.lms.models.Library;

/**
 * Created by bhushan on 25/4/17.
 */
@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    @Override
    public Invitation get(Long id) {
        return invitationRepository.getOne(id);
    }

    @Override
    public Invitation create(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    @Override
    public void delete(Long id) {
        invitationRepository.delete(id);
    }

    @Override
    public void update(Invitation invitation) {
        invitationRepository.save(invitation);
    }

    @Override
    public Page<Invitation> getPageRequest(Integer pageNumber) {
        PageRequest request =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        return invitationRepository.findAll(request);
    }

    @Override
    public Invitation findByUuid(String uuid) {
        return invitationRepository.findByUuid(uuid);
    }
}
