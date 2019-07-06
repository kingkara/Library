package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "borrows")
public class Borrow {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "books_id")
    @NotNull
    private Book book;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @NotNull
    private User user;

    @Column(name = "dateOfBorrow")
    @NotNull
    private LocalDate dateOfBorrow;

    @Column(name = "dateOfReturn")
    @NotNull
    private LocalDate dateOfReturn;
}
