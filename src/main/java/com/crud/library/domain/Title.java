package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "titles")

public class Title {

   @Id
   @GeneratedValue
   @NotNull
   @Column(name = "id")
   private long titleId;

   @Column(name = "title", unique = true)
   @NotNull
   private String title;

   @Column(name = "author")
   @NotNull
   private String author;

   @Column(name = "published")
   @NotNull
   private LocalDate yearOfPublish;

   @OneToMany (targetEntity = Book.class,
   mappedBy = "title",
   cascade = CascadeType.ALL,
   fetch = FetchType.LAZY)
   @JsonManagedReference
   private List<Book> booksList;
}
