package com.lms.services.membershipplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.dao.repository.MembershipPlanRepository;
import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;

/**
 * Created by bhushan on 3/5/17.
 */
@Service
public class MembershipPlanServiceImpl implements MembershipPlanService {
    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    @Override
    @Transactional(readOnly = true)
    public MembershipPlan get(Long id) {
        return membershipPlanRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MembershipPlan> getAll() {
        return membershipPlanRepository.findAll();
    }

    @Override
    @Transactional
    public MembershipPlan create(MembershipPlan membershipPlan) {
        return membershipPlanRepository.save(membershipPlan);
    }

    @Override
    @Transactional
    public MembershipPlan update(MembershipPlan membershipPlan) {
        return membershipPlanRepository.save(membershipPlan);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        membershipPlanRepository.delete(id);
    }

    @Override
    public MembershipPlan findByUuid(String uuid) {
        return membershipPlanRepository.findByUuid(uuid);
    }
    @Override
    public List<MembershipPlan> findByMembership(MemberShip memberShip) {
        return membershipPlanRepository.findByMembership(memberShip);
    }

}
