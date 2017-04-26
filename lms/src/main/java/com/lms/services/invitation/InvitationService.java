package com.lms.services.invitation;

import org.springframework.data.domain.Page;

import com.lms.models.Book;
import com.lms.models.Invitation;

/**
 * Created by bhushan on 25/4/17.
 */
public interface InvitationService {
    Invitation get(Long id);
    Invitation create(Invitation invitation);
    void delete(Long id);
    void update(Invitation invitation);
    Page<Invitation> getPageRequest(Integer pageNumber);
    Invitation findByUuid(String uuid);
}
