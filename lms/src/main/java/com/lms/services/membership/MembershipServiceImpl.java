package com.lms.services.membership;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.models.MemberShip;
import com.lms.models.User;
import com.lms.dao.repository.MemberShipRepository;

/**
 * Created by bhushan on 9/4/17.
 */
@Service
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    private MemberShipRepository memberShipRepository;

    @Override
    public MemberShip get(Long id) {
       return memberShipRepository.getOne(id);
    }

    @Override
    public List<MemberShip> getAll() {
        return memberShipRepository.findAll();
    }

    @Override
    public MemberShip create(MemberShip memberShip) {
        return memberShipRepository.save(memberShip);
    }

    @Override
    public void delete(Long id) {
        memberShipRepository.delete(id);
    }

    @Override
    public void update(MemberShip memberShip) {
        memberShipRepository.save(memberShip);
    }

    @Override
    public MemberShip getLastUserMembership(User user) {
        List<MemberShip> memberShips =memberShipRepository.findByUserAndLastUsedTrue(user);
        if (memberShips != null) {
           return memberShips.get(0);
        }
        return null;
    }

    @Override
    public MemberShip findByUuid(String uuid) {
        return memberShipRepository.findByUuid(uuid);
    }
}
