package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TitleDto {
    private long titleId;
    private String title;
    private String author;
    private LocalDate yearOfPublish;
}
