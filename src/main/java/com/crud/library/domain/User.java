package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true)
    @NotNull
    private String firstName;

    @Column(name = "surname")
    @NotNull
    private String lastName;

    @Column(name = "date_of_create")
    @NotNull
    private LocalDate dateOfCreatingAccount;

    @OneToMany(targetEntity = Borrow.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Borrow> borrowList;
}
