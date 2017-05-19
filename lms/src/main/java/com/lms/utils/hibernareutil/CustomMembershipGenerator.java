package com.lms.utils.hibernareutil;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.lms.models.MemberShip;

/**
 * Created by bhushan on 19/5/17.
 */
public class CustomMembershipGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        Session session1 = (Session)session;
        Query query = session1.createQuery("select from MemberShip order by id desc limit 1");
        List<MemberShip> memberShips = query.list();
        if (memberShips != null ) {
            Long id = memberShips.get(0).getId();
            return String.format("%s-%s", "LMS", id);
        } else {
            return String.format("%s-%s", "LMS", 0);
        }

    }
}
