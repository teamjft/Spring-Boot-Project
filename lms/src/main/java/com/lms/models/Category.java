package com.lms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 8/4/17.
 */
@Entity
@Setter @Getter
public class Category extends AbstractEntity implements Serializable{
    private static final long serialVersionUID = 7059377981679206071L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

}
