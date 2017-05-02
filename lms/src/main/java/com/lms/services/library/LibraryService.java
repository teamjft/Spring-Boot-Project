package com.lms.services.library;

import java.util.List;

import com.lms.models.Library;
import com.lms.utils.beans.LibraryDataCount;


/**
 * Created by bhushan on 9/4/17.
 */
public interface LibraryService {
    Library get(Long id);
    List<Library> getAll();
    Library create(Library library);
    void delete(Long id);
    void update(Library library);
    Library findByUuid(String uuid);
    LibraryDataCount basicCountInfoOfLibrary(String libraryUuid);
}
