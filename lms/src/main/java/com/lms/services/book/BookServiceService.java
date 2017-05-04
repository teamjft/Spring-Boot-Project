package com.lms.services.book;

import static com.lms.utils.Constant.PAGE_SIZE;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lms.config.interceptor.CustomXssHandlerInterceptor;
import com.lms.config.interceptor.OptionalXssFiltersConfiguration;
import com.lms.models.Book;
import com.lms.dao.repository.BookRepository;
import com.lms.models.Library;
import com.lms.utils.customannotation.annotaion.XxsFilter;

/**
 * Created by bhushan on 17/4/17.
 */
@Service
public class BookServiceService implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Book get(Long id) {
        return bookRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
       return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    @Transactional
    public void update(Book books) {
         bookRepository.save(books);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> getPageRequest(Integer pageNumber) {
        PageRequest request =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        return bookRepository.findAll(request);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countBYLibraryAndIsbn(Library library, String isbn) {
        return bookRepository.countByLibraryAndIsbn(library, isbn);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByUuid(String uuid) {
        return bookRepository.findByUuid(uuid);
    }


   /* @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @PostConstruct
    public void init() {
        System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
        Map<RequestMappingInfo, HandlerMethod> handlerMethods =
                this.requestMappingHandlerMapping.getHandlerMethods();

        for(Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            RequestMappingInfo mapping = item.getKey();
            HandlerMethod method = item.getValue();
            for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
                if(method.hasMethodAnnotation(XxsFilter.class)) {
                    System.out.println(
                            method.getBeanType().getName() + "#" + method.getMethod().getName() +
                                    " <------------- " +urlPattern);
                }
                if (urlPattern.equals("some specific url")) {
                    //add to list of matching METHODS
                }
            }
        }

    }*/
   @PostConstruct
   public void init() {
       someFilterRegistration();
   }

    @Bean
    @Conditional(OptionalXssFiltersConfiguration.class)
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CustomXssHandlerInterceptor());
        String patters[] = OptionalXssFiltersConfiguration.urls.stream().toArray(String[]::new);
        registration.addUrlPatterns(patters);
        return registration;

    }
}
