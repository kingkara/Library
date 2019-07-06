package com.crud.library.repository;

import com.crud.library.domain.Title;
import org.springframework.data.repository.CrudRepository;

public interface TitleRepository  extends CrudRepository<Title, Long> {
    @Override
    Title save(Title title);

    Title getByTitleEquals(String title);

}
