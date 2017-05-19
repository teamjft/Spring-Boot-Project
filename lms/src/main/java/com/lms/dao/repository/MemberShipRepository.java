package com.lms.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.User;

/**
 * Created by bhushan on 10/4/17.
 */
public interface MemberShipRepository extends JpaRepository<MemberShip, Long> {
       public List<MemberShip> findByUserAndLastUsedTrue(User user);
       MemberShip findByUuid(String uuid);
       MemberShip findByLibraryAndUser(Library library, User user);
       MemberShip findFirstByOrderByIdDesc();


}
