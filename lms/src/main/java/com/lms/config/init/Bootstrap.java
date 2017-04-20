package com.lms.config.init;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.lms.models.Book;
import com.lms.models.Category;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.User;
import com.lms.dao.LibraryDao;
import com.lms.services.book.BookService;
import com.lms.services.category.CategoryService;
import com.lms.utils.factory.ImageFactory;
import com.lms.services.library.LibraryService;
import com.lms.services.membership.MembershipService;
import com.lms.services.user.UserService;
import com.lms.utils.modelutil.MembershipStatus;

/**
 * Created by bhushan on 13/2/17.
 */
@Component
@Slf4j
public class Bootstrap implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private LibraryDao libraryDao;
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageFactory imageFactory;

   /* @Autowired
    private ConversionService conversionService;*/
    /**
     * Create a default users with ADMIN and USER role(if require) at the time of onApplicationEvent.
     * */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (userService.count() == 0) {
            log.info("Bootstrap application with default users and roles");
            User user = new User();
            user.setEmail("a@b.com");
            user.setFirstName("Admin");
            user.setUsername("admin");
            user.setPassword("password");
            user = userService.createUser(user);

            Library library = new Library();
            library.setName("DU");
            library.setDescription("Test data");
            library.setEmail("lib@lib.com");
            library = libraryService.create(library);

            MemberShip memberShip = new MemberShip();
            memberShip.setAdmin(true);
            memberShip.setLastUsed(true);
            memberShip.setMembershipStatus(MembershipStatus.ACTIVE);
            memberShip.setUser(user);
            memberShip.setLastUsed(true);
            memberShip.setLibrary(library);
            membershipService.create(memberShip);

            Book book = new Book();
            book.setLibrary(library);
            book.setName("Selp-Helf");
            book.setDescription("In this decidedly unhelpful, candid, hilarious “how-to” guide, YouTube personality Miranda Sings offers life lessons and tutori-als with her signature sassy attitude. Over six million social media fans can’t be wrong: Miranda Sings is one of the funniest faces on YouTube. As a bumbling, ironically talentless, self-absorbed personality (a young Gilda Radner, if you will), she offers up a vlog of helpful advice every week on her widely popular YouTube channel. For the first time ever, Miranda is putting her advice to paper in this easy-to-follow guide, illus-trated by Miranda herself. In it, you’ll find instructions on everything: how to get a boyfriend (wear all black and carry a fishing net), to dressing for a date (sequins and an orange tutu), to performing magic (“Magic is Lying”), and much, much more! Miranda-isms abound in these self-declared lifesaving pages, and if you don’t like it…well, as Miranda would say…“Haters, back off!” .");
            book.setAuthorName("Miranda Sings ");
            book.setIsbn("978-1471144806");
            book.setNumberOfAvailableCopies(100);
            book.setTotalNumberOfCopies(100);
            book.setPrice(125d);
            bookService.create(book);

            Category category = new Category();
            category.setName("Fashion boot");
            Category category1 = new Category();
            category1.setName("Chap boot");
            categoryService.create(category);
            categoryService.create(category1);
        }
    }


}
