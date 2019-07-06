package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    @NotNull
    private Title title;

    @Column(name = "status")
    @NotNull
    private String status;


}
