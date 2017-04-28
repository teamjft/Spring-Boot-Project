package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Library;

/**
 * Created by bhushan on 12/4/17.
 */
public interface LibraryRepository extends JpaRepository<Library, Long> {
    Library findByUuid(String uuid);
}
