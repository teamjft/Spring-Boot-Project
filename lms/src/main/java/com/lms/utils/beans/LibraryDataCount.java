package com.lms.utils.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bhushan on 16/4/17.
 */
@Getter
public class LibraryDataCount {
    private Long numberOFBook;
    private Long numberOFActiveUser;
    private Long totalNumberOfCopies;
    private Long numberOFIssuedBook;

    public LibraryDataCount(Long numberOFBook, Long numberOFActiveUser, Long totalNumberOfCopies, Long numberOFIssuedBook) {
        this.numberOFBook = numberOFBook;
        this.numberOFActiveUser = numberOFActiveUser;
        this.totalNumberOfCopies = totalNumberOfCopies;
        this.numberOFIssuedBook = numberOFIssuedBook;
    }
    public LibraryDataCount(Long numberOFBook, Long numberOFActiveUser, Integer totalNumberOfCopies, Long numberOFIssuedBook) {
        this.numberOFBook = numberOFBook;
        this.numberOFActiveUser = numberOFActiveUser;
        this.totalNumberOfCopies = new Long(totalNumberOfCopies);
        this.numberOFIssuedBook = numberOFIssuedBook;
    }

    @Override
    public String toString() {
        return "DataCount{" +
                "numberOFBook=" + numberOFBook +
                ", numberOFActiveUser=" + numberOFActiveUser +
                ", numberOFAvailableBookCopies=" + totalNumberOfCopies +
                ", numberOFIssuedBook=" + numberOFIssuedBook +
                '}';
    }
}
