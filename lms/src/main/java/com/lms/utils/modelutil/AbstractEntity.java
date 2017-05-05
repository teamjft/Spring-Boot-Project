package com.lms.utils.modelutil;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;

/**
 * Created by bhushan on 8/4/17.
 */
@MappedSuperclass
@Getter @Setter
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updateOn = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdOn = new Date();

    @Column(name = "uuid", unique = true, updatable = false)
    private String uuid;
    @Type(type = "yes_no")
    @Column(name = "is_enabled", nullable = false, columnDefinition = "char(1) default 'Y'")
    private boolean enabled = true;

    @PrePersist
    protected void onPrePersist() {
        setUuid(UUID.randomUUID().toString());
    }
}
