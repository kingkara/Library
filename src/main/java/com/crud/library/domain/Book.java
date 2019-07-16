package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.crud.library.domain.Constant.AVAILABLE;
import static com.crud.library.domain.Constant.BORROWED;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "titles_id")
    @NotNull
    private Title title;

    @Column(name = "status")
    @NotNull
    private String status;

    public void borrowBook() {
        this.status = BORROWED;
    }

    public void returnBook() {
        this.status = AVAILABLE;
    }
}
