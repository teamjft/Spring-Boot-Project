package com.lms.services.membership;

import java.util.List;

import javax.jws.soap.SOAPBinding;

import org.springframework.data.domain.Page;

import com.lms.models.Category;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.User;

/**
 * Created by bhushan on 9/4/17.
 */
 public interface MembershipService {
     MemberShip get(Long id);
     List<MemberShip> getAll();
     MemberShip create(MemberShip memberShip);
     void delete(Long id);
     void update(MemberShip memberShip);
     MemberShip getLastUserMembership(User user);
     MemberShip findByUuid(String uuid);
     MemberShip findByLibraryAndUser(Library library, User user);
    Page<MemberShip> getPageRequest(Integer pageNumber);
}
