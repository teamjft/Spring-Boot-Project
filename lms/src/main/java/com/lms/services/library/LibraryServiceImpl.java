package com.lms.services.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.dao.LibraryDao;
import com.lms.models.Library;
import com.lms.dao.repository.LibraryRepository;
import com.lms.utils.beans.DataCount;

/**
 * Created by bhushan on 9/4/17.
 */
@Service
public class LibraryServiceImpl  implements LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryDao libraryDao;

    @Transactional(readOnly = true)
    @Override
    public Library get(Long id) {
        return libraryRepository.getOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Library> getAll() {
        return libraryRepository.findAll();
    }

    @Transactional
    @Override
    public Library create(Library library) {
        if(library.getId() != null) {
            return null;
        }
        return libraryRepository.save(library);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        libraryRepository.delete(id);
    }

    @Transactional
    @Override
    public void update(Library library) {
        libraryRepository.save(library);
    }

    @Override
    public Library findByUuid(String uuid) {
        return libraryRepository.findByUuid(uuid);
    }

    @Override
    public DataCount basicCountInfoOfLibrary(String libraryUuid) {
        return libraryDao.basicCountInfoOfLibrary(libraryUuid);
    }
}
