package com.lms.services.membershipsubscription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.dao.repository.MembershipSubscriptionRepository;
import com.lms.models.MemberShip;
import com.lms.models.MembershipSubscription;
import com.lms.utils.helper.PaginationHelper;

/**
 * Created by bhushan on 17/5/17.
 */
@Service
public class MembershipSubscriptionServiceImpl implements MembershipSubscriptionService {
    @Autowired
    private MembershipSubscriptionRepository membershipSubscriptionRepository;

    @Override
    @Transactional(readOnly = true)
    public MembershipSubscription get(Long id) {
        return membershipSubscriptionRepository.getOne(id);
    }

    @Override
    public MembershipSubscription findByUuid(String libraryId, String uuid) {
        return membershipSubscriptionRepository.findBySubscriptionUuidAndLibraryUuid(libraryId, uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MembershipSubscription> getAll() {
        return membershipSubscriptionRepository.findAll();
    }

    @Override
    @Transactional
    public MembershipSubscription create(MembershipSubscription membershipSubscription) {
        return membershipSubscriptionRepository.save(membershipSubscription);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        membershipSubscriptionRepository.delete(id);
    }

    @Override
    @Transactional
    public void update(MembershipSubscription membershipSubscription) {
        membershipSubscriptionRepository.save(membershipSubscription);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MembershipSubscription> getPageRequest(String libraryId, Integer pageNumber) {
        return membershipSubscriptionRepository.findByLibraryUuid(libraryId, PaginationHelper.getPageRequest(pageNumber));
    }
}
