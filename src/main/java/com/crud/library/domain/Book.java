package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
        this.status = "borrowed";
    }

    public void returnBook() {
        this.status = "available";
    }
}
